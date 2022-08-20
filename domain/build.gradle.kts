plugins {
    kotlin
}

dependencies {
    implementation(LibraryDependency.RETROFIT)
    implementation(LibraryDependency.COROUTINES_CORE)
    implementation(LibraryDependency.KOIN)
    implementation(LibraryDependency.ARROW_FX)
    addTestDependencies()
}
