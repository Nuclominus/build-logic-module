package plugin.lint

import ext.Configurations
import ext.addLibrary
import ext.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationCodeCheckPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        pluginManager.apply("io.gitlab.arturbosch.detekt")

        configureCodeChecking()

        dependencies {
            addLibrary(versionCatalog(), "detekt-formatter", Configurations.DetektPlugins)
        }
    }
}
