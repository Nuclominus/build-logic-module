package data

import org.gradle.api.JavaVersion

object AndroidAppConf {
    const val APP_VERSION: String = "0.0.1"
    const val VERSION_CODE: Int = 1
    const val NAMESPACE: String = "io.github.nuclominus.buildlogic.template"
    const val COMPILE_SDK: Int = 34
    const val MIN_SDK: Int = 26
    val javaVersion: JavaVersion = JavaVersion.VERSION_19
}

object LibConf {
    const val LIB_VERSION: String = "0.0.1"
    const val NAMESPACE: String = "name of your package"
}
