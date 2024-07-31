package plugin.lint

import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType

/**
 * Configures code checking for the project using Detekt.
 *
 * This function sets up the Detekt extension with the specified source directories,
 * configuration file, and various options such as building upon the default configuration,
 * enabling auto-correction, and parallel processing. It also excludes certain files from
 * Detekt analysis.
 */
internal fun Project.configureCodeChecking() =
    extensions.getByType<io.gitlab.arturbosch.detekt.extensions.DetektExtension>().apply {
        // Set the source directories for Detekt analysis
        source.setFrom(
            "src/main/kotlin",
            "src/main/java"
        )
        // Set the Detekt configuration file
        config.setFrom("../config/detekt/detekt.yaml")
        // Enable building upon the default Detekt configuration
        buildUponDefaultConfig = true
        // Enable auto-correction of issues
        autoCorrect = true
        // Enable parallel processing
        parallel = true
        // Exclude certain files from Detekt analysis
        tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
            exclude("**/theme/**/*.kt")
        }
    }
