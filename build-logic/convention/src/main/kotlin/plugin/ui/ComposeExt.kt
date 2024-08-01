@file:Suppress("UnstableApiUsage")

package plugin.ui

import com.android.build.api.dsl.ApplicationExtension
import ext.compilerOptions
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import java.io.File

/**
 * Configure Compose-specific options
 */
internal fun Project.configureCompose() = extensions.getByType<ApplicationExtension>().apply {
    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

    buildFeatures {
        compose = true
    }

    compilerOptions {
        freeCompilerArgs.set(
            freeCompilerArgs.get() + buildComposeMetricsParameters()
        )
    }
}

/**
 * Configure Compose-specific options for Kotlin
 * Add metrics and reports parameters if enabled
 */
private fun Project.buildComposeMetricsParameters(): List<String> {
    val buildDirectory: File = layout.buildDirectory.get().asFile
    val metricParameters = mutableListOf<String>()
    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
    val enableMetrics = (enableMetricsProvider.orNull == "true")
    if (enableMetrics) {
        val metricsFolder = File(buildDirectory, "compose-metrics")
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath
        )
    }

    val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
    val enableReports = (enableReportsProvider.orNull == "true")
    if (enableReports) {
        val reportsFolder = File(buildDirectory, "compose-reports")
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + reportsFolder.absolutePath
        )
    }
    return metricParameters.toList()
}
