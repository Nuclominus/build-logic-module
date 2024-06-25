@file:Suppress("UnstableApiUsage")

rootProject.name = "BuildLogicTemplate"

pluginManagement {
    includeBuild("build-logic")
    gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses")) // Exclude testClasses task from build-logic if you don't want to run tests
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(":app")
