package utils

import appconfig.AppModules
import extensions.implementation
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.includeModules(vararg modules: AppModules) = modules.forEach {
    implementation(project(it.moduleName))
}