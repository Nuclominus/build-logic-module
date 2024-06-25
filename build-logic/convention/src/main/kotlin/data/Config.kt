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

object MavenConf {
    const val GROUP_ID: String = "name of your package"
    const val ARTIFACT_ID: String = "name of your library"
    const val LIB_VERSION: String = LibConf.LIB_VERSION
    const val ARTIFACT_NAME: String = "name of your library"
    const val DESCRIPTION: String = "description of your library"
    const val URL: String = "url of your library"
    const val LICENSE_NAME: String = "license name"
    const val DEVELOPER_ID: String = "your id"
    const val DEVELOPER_NAME: String = "your name"
    const val DEVELOPER_EMAIL: String = "your email"
    const val SCM_URL: String = "url of your scm"
    const val MAVEN_NAME: String = "sonatypeStaging"
    const val MAVEN_URL: String = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
    const val OSS_USERNAME: String = "OSS_USERNAME"
    const val OSS_PASSWORD: String = "OSS_PASSWORD"
}
