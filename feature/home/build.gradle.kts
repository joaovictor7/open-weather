import appconfig.AppModule
import utils.includeModules

plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.compose)
    alias(libs.plugins.composeTest.test)
}

android {
    namespace = "com.composetest.feature.home"
}

dependencies {
    includeModules(
        AppModule.CORE,
        AppModule.CORE_UI,
        AppModule.ROUTER
    )
}