package plugin.ui

import ext.Configurations
import ext.addBundle
import ext.addLibrary
import ext.addPlatform
import ext.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidComposePlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {

        configureCompose()

        dependencies {
            // Compose dependencies
            addPlatform(versionCatalog(), "compose-bom")
            addBundle(versionCatalog(), "compose")

            // Test dependencies
            addPlatform(versionCatalog(), "compose-bom", Configurations.TestImplementation)
            addLibrary(versionCatalog(), "compose-ui-test-junit4", Configurations.TestImplementation)
            addLibrary(versionCatalog(), "compose-ui-tooling", Configurations.DebugImplementation)
            addLibrary(versionCatalog(), "compose-ui-test-manifest", Configurations.DebugImplementation)

            addLibrary(versionCatalog(), "compose-ui-test-junit4", Configurations.TestImplementation)
            addLibrary(versionCatalog(), "compose-ui-test-manifest", Configurations.DebugImplementation)
        }
    }

}
