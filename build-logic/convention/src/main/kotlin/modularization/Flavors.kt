package modularization

import appconfig.AppFlavors
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

internal fun BaseAppModuleExtension.setFlavors() {
    val defaultDimension = "flavorType"
    flavorDimensions += defaultDimension
    productFlavors {
        AppFlavors.values().forEach { flavor ->
            create(flavor.flavorName) {
                dimension = defaultDimension
            }
        }
    }
}