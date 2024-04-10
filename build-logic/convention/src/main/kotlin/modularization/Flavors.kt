package modularization

import appconfig.AppFlavors
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

internal fun BaseAppModuleExtension.setFlavors() {
    flavorDimensions += AppFlavors.values().map { it.flavorName }
    productFlavors {
        AppFlavors.values().forEach { flavor ->
            create(flavor.flavorName)
        }
    }
}