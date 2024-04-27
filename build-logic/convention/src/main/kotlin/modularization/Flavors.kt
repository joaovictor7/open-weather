package modularization

import appconfig.AppFlavors
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

internal fun BaseAppModuleExtension.setFlavors() {
    val dimensions = "flavorDimension"
    flavorDimensions += dimensions
    productFlavors {
        AppFlavors.values().forEach { flavor ->
            create(flavor.flavorName) {
                dimension = dimensions
                isDefault = flavor.isDefault
            }
        }
    }
}