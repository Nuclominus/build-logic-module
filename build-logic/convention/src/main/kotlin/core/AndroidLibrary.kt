package core

import com.android.build.api.dsl.LibraryExtension
import ext.addLibrary
import ext.versionCatalog
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType


internal fun Project.configureAndroidLibrary() =
    extensions.getByType<LibraryExtension>().apply {
        configureAndroidDefaults()

        dependencies {
            addLibrary(versionCatalog(), "androidx-core-ktx")
        }
    }
