plugins {
    `kotlin-dsl`
}

group = "io.nuclominus.buildlogic.convention"

java {
    sourceSets {
        main {
            kotlin {
                srcDir("src/main/kotlin")
            }
        }
    }
}

dependencies {
    compileOnly(libs.bundles.gradle.plugins)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("application") {
            id = "io.nuclominus.android.application"
            implementationClass = "convention.AndroidApplicationConventionPlugin"
        }
        register("library") {
            id = "io.nuclominus.android.library"
            implementationClass = "convention.AndroidLibraryConventionPlugin"
        }
        register("codeCheck") {
            id = "io.nuclominus.code.check"
            implementationClass = "plugin.lint.AndroidApplicationCodeCheckPlugin"
        }
        register("di") {
            id = "io.nuclominus.di"
            implementationClass = "plugin.di.AndroidDependencyInjectionPlugin"
        }
        register("db") {
            id = "io.nuclominus.db"
            implementationClass = "plugin.db.AndroidDataBasePlugin"
        }
        register("compose") {
            id = "io.nuclominus.android.compose"
            implementationClass = "plugin.ui.AndroidComposePlugin"
        }
    }
}
