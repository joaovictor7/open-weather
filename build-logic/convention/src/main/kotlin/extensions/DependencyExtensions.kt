package extensions

import org.gradle.kotlin.dsl.DependencyHandlerScope

private fun DependencyHandlerScope.implement(configurationName: String, dependencyNotation: Any) =
    add(configurationName, dependencyNotation)

internal fun DependencyHandlerScope.implementation(dependencyNotation: Any) =
    implement("implementation", dependencyNotation)

internal fun DependencyHandlerScope.debugImplementation(dependencyNotation: Any) =
    implement("debugImplementation", dependencyNotation)

internal fun DependencyHandlerScope.androidTestImplementation(dependencyNotation: Any) =
    implement("androidTestImplementation", dependencyNotation)

internal fun DependencyHandlerScope.testImplementation(dependencyNotation: Any) =
    implement("testImplementation", dependencyNotation)