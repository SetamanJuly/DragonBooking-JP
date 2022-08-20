import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/*
Define common dependencies, so they can be easily updated across feature modules
 */
fun DependencyHandler.addTestDependencies() {
    testImplementation(TestLibraryDependency.JUNIT)
    testImplementation(TestLibraryDependency.KOIN)
    testImplementation(TestLibraryDependency.ESPRESSO)
    testImplementation(TestLibraryDependency.MOCKK)
    testImplementation(TestLibraryDependency.COROUTINES_TEST)
    testImplementation(TestLibraryDependency.MOCKITO)
    testImplementation(TestLibraryDependency.MOCKITO_INLINE)
    testImplementation(TestLibraryDependency.MOCKITO_KOTLIN)
}

fun DependencyHandler.addAndroidTestDependencies() {
    androidTestImplementation(TestLibraryDependency.JUNIT_ANDROID)
    androidTestImplementation(TestLibraryDependency.ESPRESSO)
    androidTestImplementation(TestLibraryDependency.MOCKITO)
    androidTestImplementation(TestLibraryDependency.MOCKITO_INLINE)
    androidTestImplementation(TestLibraryDependency.MOCKITO_KOTLIN)
}

/*
 * These extensions mimic the extensions that are generated on the fly by Gradle.
 * They are used here to provide above dependency syntax that mimics Gradle Kotlin DSL syntax in module\build.gradle.kts files.
 */
fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

private fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)
