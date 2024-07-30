package core

import ext.Configurations
import ext.addLibrary
import ext.versionCatalog
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType

internal fun Project.configureCodeChecking() = with(pluginManager) {
    apply("io.gitlab.arturbosch.detekt")

    with(extensions.getByType<DetektExtension>()) {
        source.setFrom(
            "src/main/kotlin",
            "src/main/java"
        )

        assert(!file("../config/detekt/detekt.yaml").exists()) {
            "Detekt configuration file not found"
        }

        config.setFrom("../config/detekt/detekt.yaml")

        buildUponDefaultConfig = true
        autoCorrect = true
        parallel = true

        tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
            exclude("**/theme/**/*.kt")
        }
    }

    dependencies {
        addLibrary(versionCatalog(), "detekt-formatter", Configurations.DetektPlugins)
    }
}
