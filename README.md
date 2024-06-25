# Build-Logic Module for Android Projects
This project provides a template for a build-logic module for Android projects. It is designed to streamline the process of setting up and managing dependencies in your Android project.

### Features
- **Custom Plugins**: This project uses custom plugins to manage dependencies and configurations. The `ApplicationConventionPlugin` is used to manage application configuration and dependencies, while the `LibraryConventionPlugin` is used to manage module library also.

- **Gradle Version Catalogs**: This project uses the Gradle's version catalogs feature to centralize the dependencies and plugins in a TOML file (gradle/libs.versions.toml). This makes it easier to manage and update dependencies and plugins.

- **Bundles**: Dependencies are grouped into bundles for easier management. For example, all Hilt dependencies are grouped under the hilt bundle and all Compose dependencies are grouped under the compose bundle.

- **Maven Publish**: The library module is configured to publish the library to a Maven repository. The credentials for the repository are stored in the `MavenConf` file.

### Build-Logic Module
The build-logic module is an included build, as configured in the root `settings.gradle.kts`. Inside the build-logic module is a `convention` module, which defines a set of plugins that all normal modules can use to configure themselves. The build-logic module also includes a set of Kotlin files used to share logic between plugins themselves, which is most useful for configuring Android components (libraries vs applications) with shared code.
More details in [build-logic README.md](build-logic/README.md).

### Android Application Module
The Android application module is a sample application module that uses the build-logic module to configure itself. It uses the `ApplicationConventionPlugin` to configure common Android and Kotlin options.
Also application module configured and has such setup from box:

```toml
[bundles]
androidx = [
    "androidx-core-ktx",
    "androidx-multidex",
    "androidx-work",
    "androidx-room",
    "androidx-room-ktx",
    "androidx-lifecycle-runtime-ktx",
]
hilt = [
    "hilt-android",
    "hilt-navigation-compose",
    "hilt-work",
]
compose = [
    "compose-ui",
    "compose-ui-graphics",
    "compose-ui-tooling-preview",
    "compose-lifecycle",
    "material3-compose",
    "compose-activity",
    "compose-livedata",
]
```

This is optional and can be changed in the [`toml` file](gradle/libs.versions.toml).

### Android Library Module
The Android library module is a sample library module that uses the build-logic module `LibraryConventionPlugin` to configure common Android and Kotlin options and implemented **Maven-Publish** plugin to publish library to maven repository with provided credentials in [MavenConf](build-logic/convention/src/main/kotlin/data/Config.kt).

# Contributing
Contributions are welcome. Please open a pull request with your changes.