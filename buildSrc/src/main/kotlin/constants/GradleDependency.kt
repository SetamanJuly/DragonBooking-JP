object CoreVersion{
    const val KOTLIN = "1.7.0"
    const val NAVIGATION = "2.5.1"
    const val ANDROID_GRADLE = "7.2.1"
    const val KTLINT_GRADLE = "10.3.0"






    const val KTLINT = "0.40.0"
    const val DETEKT = "1.16.0-RC1"
    const val VERSIONS_PLUGIN = "0.36.0"
}

object GradlePluginId {
    const val ANDROID_APP = "com.android.application"
    const val ANDROID_LIB  = "com.android.library"
    const val ANDROID = "kotlin-android"
    const val SAFE_ARGS = "androidx.navigation.safeargs"
    const val BASE_GRADLE_PLUGIN = "base-gradle-plugin"
    const val KTLINT_GRADLE = "org.jlleitschuh.gradle.ktlint"
    const val KTLINT_MAVEN =  "https://plugins.gradle.org/m2/"
    const val DETEKT = "io.gitlab.arturbosch.detekt"
    const val VERSIONS_PLUGIN = "com.github.ben-manes.versions"
}

object GradleClasspath {
    const val KOTLIN_PlUGIN = "gradle-plugin"
    const val ANDROID_GRADLE = "com.android.tools.build:gradle:${CoreVersion.ANDROID_GRADLE}"
    const val SAFE_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:${CoreVersion.NAVIGATION}"
    const val KTLINT_CLASSPATH = "org.jlleitschuh.gradle:ktlint-gradle:${CoreVersion.KTLINT_GRADLE}"
}
