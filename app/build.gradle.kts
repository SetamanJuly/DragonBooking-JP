plugins {
    id(GradlePluginId.ANDROID_APP)
    id(GradlePluginId.BASE_GRADLE_PLUGIN)
}

android.buildFeatures {
    viewBinding = true
}

android.packagingOptions {
    resources.excludes.add("META-INF/**")
    resources.excludes.add("kotlin/reflect/reflect.kotlin_builtins")
    resources.excludes.add("**/kotlin/**")
    resources.excludes.add("/META-INF/*.kotlin_module")
    resources.excludes.add("**/*.txt")
    resources.excludes.add("**/*.xml")
    resources.excludes.add("**/*.properties")
}

dependencies {
    implementation(project(ModulesDependency.CACHE))
    implementation(project(ModulesDependency.REMOTE))
    implementation(project(ModulesDependency.DATA))
    implementation(project(ModulesDependency.DOMAIN))
    implementation(project(ModulesDependency.PRESENTATION))
}
