import appconfig.AppModule
import utils.includeModules

plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.compose)
    alias(libs.plugins.composeTest.test)
}

android {
    namespace = "com.composetest.feature.login"
}

dependencies {
    includeModules(
        AppModule.CORE_TEST,
        AppModule.CORE_UI,
        AppModule.CORE_ROUTER,
        AppModule.CORE_DATA,
        AppModule.COMMON_UTILITY,
        AppModule.COMMON_DESIGNSYSTEM
    )
}