plugins {
    kotlin
}

dependencies {
    implementation(LibraryDependency.RETROFIT)
    implementation(LibraryDependency.RETROFIT_CONVERTER)
    implementation(LibraryDependency.RETROFIT_INTERCEPTOR)
    implementation(LibraryDependency.COLLECTION_KTX)
    implementation(LibraryDependency.KOIN)
    implementation(LibraryDependency.ARROW_FX)
    implementation(project(ModulesDependency.DATA))
}
