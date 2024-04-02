package utils

import appconfig.AppModule
import extensions.implementation
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

internal fun DependencyHandlerScope.includeAllModules() {
    AppModule.values().forEach { module ->
        includeModule(module)
    }
}

fun DependencyHandlerScope.includeModule(module: AppModule) {
    implementation(project(module.moduleName))
}