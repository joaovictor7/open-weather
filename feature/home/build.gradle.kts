import appconfig.AppModules
import utils.includeModules

plugins {
    alias(libs.plugins.composeTestLibrary)
    alias(libs.plugins.composeTestCompose)
    alias(libs.plugins.composeTestTest)
}

android {
    namespace = "com.composetest.feature.home"
}

dependencies {
    includeModules(
        AppModules.CORE,
        AppModules.ROUTER
    )
}