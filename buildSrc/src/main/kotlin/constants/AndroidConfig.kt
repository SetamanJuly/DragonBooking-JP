object AndroidConfig {
    const val COMPILE_SDK_VERSION = 32
    const val MIN_SDK_VERSION = 27
    const val TARGET_SDK_VERSION = 32
    const val VERSION_CODE = 1
    const val VERSION_NAME = "0.1"

    const val ID = "com.julianparrilla.dragonbooker"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}


interface BuildType {
    companion object {
        const val RELEASE = "release"
        const val DEBUG = "debug"
    }
}