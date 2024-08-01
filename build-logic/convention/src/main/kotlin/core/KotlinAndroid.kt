@file:Suppress("UnstableApiUsage")

package core

import ext.compilerOptions
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import java.io.File

private enum class CompileArgs(val value: String) {
    RequiresOptIn("-opt-in=kotlin.RequiresOptIn"),
    JvmDefault("-Xjvm-default=all-compatibility"),
    ContentReceivers("-Xcontext-receivers");

    companion object {
        val values = values().map { it.value }
    }
}

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlin() = compilerOptions {
    // Treat all Kotlin warnings as errors (disabled by default)
    // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
    val warningsAsErrors: String? by project
    allWarningsAsErrors.set(warningsAsErrors.toBoolean())

    freeCompilerArgs.set(freeCompilerArgs.get() + CompileArgs.values)

    jvmTarget.set(JvmTarget.JVM_19)
}
