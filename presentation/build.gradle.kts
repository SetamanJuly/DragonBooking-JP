plugins {
    id(GradlePluginId.ANDROID_LIB)
    id(GradlePluginId.BASE_GRADLE_PLUGIN)
    id(GradlePluginId.SAFE_ARGS)
    `kotlin-kapt`
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    sourceSets {
        getByName("main") {
            java {
                srcDirs("src/main/java")
            }
        }
        getByName("test") {
            java {
                srcDirs("src/test/java")
            }
        }
        getByName("androidTest") {
            java {
                srcDirs("src/androidTest/java")
            }
        }
    }
}

android.buildFeatures {
    viewBinding = true
}

android.packagingOptions {
    resources.excludes.add("META-INF/**")
    resources.excludes.add("**/kotlin/**")
}

dependencies {
    implementation(LibraryDependency.CONSTRAINT)
    implementation(LibraryDependency.APPCOMPAT)
    implementation(LibraryDependency.MATERIAL)
    implementation(LibraryDependency.RECYCYLER_VIEW)
    implementation(LibraryDependency.CARD_VIEW)
    implementation(LibraryDependency.NAVIGATION_FRAGMENT)
    implementation(LibraryDependency.NAVIGATION_UI)
    implementation(LibraryDependency.NAVIGATION_RUNTIME)
    implementation(LibraryDependency.MATERIAL_DIALOG)
    implementation(LibraryDependency.CORE_KTX)
    implementation(LibraryDependency.GLIDE)
    implementation(LibraryDependency.LOTTIE)
    implementation(LibraryDependency.LIVE_DATA_RUNTIME)
    implementation(LibraryDependency.LIVE_DATA_KTX)
    implementation(LibraryDependency.OKHTTP)
    kapt(LibraryDependency.GLIDE_COMPILAR)
    kapt(LibraryDependency.LIVE_DATA_COMPILER)
    implementation(project(ModulesDependency.DOMAIN))
    addTestDependencies()
    addAndroidTestDependencies()
}
