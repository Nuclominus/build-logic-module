package plugin.db

import androidx.room.gradle.RoomExtension
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureDataBase() {
    extensions.configure<KspExtension> {
        println("Configuring KSP for Room")
        arg("room.generateKotlin", "true")
    }

    extensions.configure<RoomExtension> {
        println("Configuring Room")
        // The schemas directory contains a schema file for each version of the Room database.
        // This is required to enable Room auto migrations.
        // See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
        schemaDirectory("$projectDir/schemas")
    }
}
