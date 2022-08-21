pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

rootProject.name = "DragonBooker-JPE"

// this gradle version has an issue with settings kts file to read from constants
include(
    "app"
)
include(":presentation")
include(":cache")
include(":data")
include(":domain")
include(":remote")
