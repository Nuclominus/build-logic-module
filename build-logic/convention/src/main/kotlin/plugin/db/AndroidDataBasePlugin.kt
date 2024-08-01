package plugin.db

import ext.Configurations
import ext.addLibrary
import ext.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDataBasePlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {

        with(pluginManager) {
            apply("com.google.devtools.ksp")
            apply("androidx.room")
        }

        configureDataBase()

        dependencies {

            addLibrary(versionCatalog(), "google-gson")

            // Room
            addLibrary(versionCatalog(), "androidx-room")
            addLibrary(versionCatalog(), "androidx-room-compiler", Configurations.Ksp)
        }
    }
}
