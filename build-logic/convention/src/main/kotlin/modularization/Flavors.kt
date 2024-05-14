package modularization

import appconfig.AppFlavor
import appconfig.AppFlavor.Companion.allDimensions
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

internal fun BaseAppModuleExtension.setFlavors() {
    flavorDimensions.addAll(allDimensions)
    productFlavors {
        AppFlavor.values().forEach { flavor ->
            create(flavor.flavorName) {
                dimension = flavor.dimension
                isDefault = flavor.isDefault
            }
        }
    }
}