package utils

import appconfig.AppModule
import extensions.implementation
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.includeModules(vararg modules: AppModule) = modules.forEach {
    implementation(project(it.moduleName))
}