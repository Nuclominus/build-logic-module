plugins {
    id("io.nuclominus.android.application")
}

android {

    defaultConfig {
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
            arg("room.incremental", "true")
        }
    }

    buildTypes {
        val release by getting {
            isMinifyEnabled = true
            isDebuggable = false
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                "dexguard-release.txt",
            )
            setProperty("archivesBaseName", "Template")
        }

        val debug by getting {
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                "dexguard-release.txt",
            )
            setProperty("archivesBaseName", "Template")

            lint {
                abortOnError = false
            }
        }
    }
}

dependencies {
    // Add your dependencies here
}
