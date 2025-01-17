// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.google.ksp) apply false
    alias(libs.plugins.hilt.plugin) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.detekt.analyzer) apply false
    alias(libs.plugins.room) apply false
}

buildscript {
    dependencies {
        classpath(libs.android.gradle.plugin)
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
