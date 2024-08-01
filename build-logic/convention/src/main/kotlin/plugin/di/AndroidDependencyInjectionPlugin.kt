package plugin.di

import ext.Configurations
import ext.addBundle
import ext.addLibrary
import ext.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDependencyInjectionPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {

        with(pluginManager) {
            apply("com.google.devtools.ksp")
            apply("dagger.hilt.android.plugin")
        }

        dependencies {
            addBundle(versionCatalog(), "hilt")
            addLibrary(versionCatalog(), "hilt-compiler", Configurations.Ksp)
            addLibrary(versionCatalog(), "hilt-android-compiler", Configurations.Ksp)
        }
    }
}
