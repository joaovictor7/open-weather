import appconfig.AppModules
import utils.includeModules

plugins {
    alias(libs.plugins.composeTestLibrary)
    alias(libs.plugins.composeTestCompose)
    alias(libs.plugins.composeTestTest)
}

android {
    namespace = "com.composetest.feature.login"
}

dependencies {
    includeModules(
        AppModules.CORE,
        AppModules.CORE_TEST,
        AppModules.ROUTER
    )
    implementation(libs.firebase.auth)
}