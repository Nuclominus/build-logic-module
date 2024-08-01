package core

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import data.AndroidAppConf
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidApplication() =
    extensions.getByType<ApplicationExtension>().apply {


        defaultConfig {
            targetSdk = AndroidAppConf.COMPILE_SDK

            versionName = AndroidAppConf.APP_VERSION
            versionCode = AndroidAppConf.VERSION_CODE
            namespace = AndroidAppConf.NAMESPACE

            multiDexEnabled = true
        }

        configureAndroidDefaults()

        lint {
            baseline = file("lint-baseline.xml")
            abortOnError = true
        }

//        configureSigningAndroid(this) // signing configuration if needed

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }


internal fun Project.configureAndroidDefaults() =
    extensions.getByType(CommonExtension::class.java).apply {
        compileSdk = AndroidAppConf.COMPILE_SDK

        defaultConfig.apply {
            minSdk = AndroidAppConf.MIN_SDK

            buildFeatures {
                // Disable unused features to reduce build time.
                // Enable features as needed in other configurations.
                aidl = false
                buildConfig = false
                dataBinding {
                    enable = false
                }
                viewBinding = false
                prefab = false
                compose = false
            }
        }

        compileOptions {
            sourceCompatibility = AndroidAppConf.javaVersion
            targetCompatibility = AndroidAppConf.javaVersion
        }
    }
