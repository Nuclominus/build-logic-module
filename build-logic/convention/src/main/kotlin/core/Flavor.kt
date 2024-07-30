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

internal fun ApplicationProductFlavor.configBuildName(flavor: Flavors) {
    if (applicationIdSuffix != null) {
        applicationIdSuffix = flavor.applicationIdSuffix
        versionNameSuffix = flavor.versionNameSuffix
    }
}

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