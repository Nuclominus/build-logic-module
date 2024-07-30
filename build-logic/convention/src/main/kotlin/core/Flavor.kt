package core

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.util.Properties

@Suppress("EnumEntryName")
enum class FlavorDimension {
    environment,
}

// The content for the app can either come from local static data which is useful for demo
// purposes, or from a production backend server which supplies up-to-date, real content.
// These two product flavors reflect this behaviour.
@Suppress("EnumEntryName")
enum class Flavors(
    val dimension: FlavorDimension,
    val applicationIdSuffix: String? = null,
    val versionNameSuffix: String? = null
) {
    development(FlavorDimension.environment, ".dev", "-development"),
    production(FlavorDimension.environment)
}

/**
 * Configure the flavors for the project.
 *
 * This function creates product flavors for each flavor in the `Flavors` enum and applies
 * configuration specific to each flavor.
 *
 * @param commonExtension The common extension to which the flavors will be applied.
 * @param flavorConfigurationBlock The block of configuration specific to each flavor.
 */
fun Project.configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: Flavors) -> Unit = {}
) {
    commonExtension.apply {

        flavorDimensions += FlavorDimension.environment.name

        productFlavors {
            Flavors.values().forEach {
                create(it.name) {
                    // main block configuration
                    dimension = it.dimension.name

                    applyConfiguration(commonExtension, name)

                    // custom configuration
                    flavorConfigurationBlock(this, it)

                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        configBuildName(it)
                    }
                }
            }
        }
    }
}

fun ProductFlavor.configureDevFlavor() {
    // custom configuration for dev flavor
}

fun ProductFlavor.configureProdFlavor() {
    // custom configuration for prod flavor
}

/**
 * Configure the build name for a specific flavor.
 *
 * @param flavor The flavor for which the build name will be configured.
 */
internal fun ApplicationProductFlavor.configBuildName(flavor: Flavors) {
    if (applicationIdSuffix != null) {
        applicationIdSuffix = flavor.applicationIdSuffix
        versionNameSuffix = flavor.versionNameSuffix
    }
}

/**
 * Apply configuration for a specific flavor.
 *
 * This function reads properties from a configuration file specific to the given flavor
 * and applies them to the `defaultConfig` block of the `commonExtension`.
 *
 * @param commonExtension The common extension to which the configuration will be applied.
 * @param configName The name of the flavor configuration to be applied.
 */
fun Project.applyConfiguration(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    configName: String,
) {
    commonExtension.apply {

        defaultConfig {
            println("Configuring flavor: $configName")
            projectDir.getProps("/config/$configName.properties").forEach { prop ->
                buildConfigField("String", "${prop.key}", "${prop.value}")
            }
        }
    }
}

private fun File.getProps(filePath: String) = Properties().apply {
    load(FileInputStream(File(parent + filePath)))
}
