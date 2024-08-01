@file:Suppress("UnstableApiUsage")

package convention

import com.android.build.api.dsl.ApplicationExtension
import core.Flavors
import core.configureAndroidApplication
import core.configureFlavors
import core.configureKotlin
import ext.Configurations
import ext.addBundle
import ext.addLibrary
import ext.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {

            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureAndroidApplication()
                configureKotlin()

                configureFlavors { flavor ->
                    when (flavor.name) {
                        Flavors.development.name -> flavor.configureDevFlavor()
                        Flavors.production.name -> flavor.configureProdFlavor()
                    }
                }
            }

            dependencies {
                addBundle(versionCatalog(), "androidx")
            }
        }
    }
}

fun Flavors.configureDevFlavor() {
    // custom configuration for dev flavor
}

fun Flavors.configureProdFlavor() {
    // custom configuration for prod flavor
}
