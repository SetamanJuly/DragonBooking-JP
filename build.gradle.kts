import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id(GradlePluginId.KTLINT_GRADLE) version CoreVersion.KTLINT_GRADLE
    id(GradlePluginId.DETEKT) version CoreVersion.DETEKT
    id(GradlePluginId.VERSIONS_PLUGIN) version CoreVersion.VERSIONS_PLUGIN
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven(GradlePluginId.KTLINT_MAVEN)
    }
    dependencies {
        classpath(GradleClasspath.ANDROID_GRADLE)
        classpath(kotlin(GradleClasspath.KOTLIN_PlUGIN, version = CoreVersion.KOTLIN))
        classpath(GradleClasspath.SAFE_ARGS)
        classpath(GradleClasspath.KTLINT_CLASSPATH)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    plugins.apply(GradlePluginId.KTLINT_GRADLE)

    ktlint {
        version.set(CoreVersion.KTLINT)
        verbose.set(true)
        android.set(true)
        reporters {
            reporter(ReporterType.CHECKSTYLE)
        }
        ignoreFailures.set(true)
        filter {
            exclude("**/generated/**")
        }
    }

    plugins.apply(GradlePluginId.DETEKT)

    detekt {
        config = files("${project.rootDir}/config/detekt.yml")
        parallel = true
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
