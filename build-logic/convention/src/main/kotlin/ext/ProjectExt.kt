package ext

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions


internal fun Project.compilerOptions(options: KotlinJvmCompilerOptions.() -> Unit) =
    extensions.configure<KotlinAndroidProjectExtension> {
        compilerOptions {
            options()
        }
    }
