package extensions

import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.implementation(dependencyNotation: Any) =
    implement("implementation", dependencyNotation)

internal fun DependencyHandlerScope.debugImplementation(dependencyNotation: Any) =
    implement("debugImplementation", dependencyNotation)

internal fun DependencyHandlerScope.testImplementation(dependencyNotation: Any) =
    implement("testImplementation", dependencyNotation)

internal fun DependencyHandlerScope.testRuntimeOnly(dependencyNotation: Any) =
    implement("testRuntimeOnly", dependencyNotation)

internal fun DependencyHandlerScope.kapt(dependencyNotation: Any) =
    implement("kapt", dependencyNotation)

private fun DependencyHandlerScope.implement(configurationName: String, dependencyNotation: Any) =
    add(configurationName, dependencyNotation)