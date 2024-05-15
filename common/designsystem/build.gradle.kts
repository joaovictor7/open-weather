import appconfig.AppModule
import utils.includeModules

plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.compose)
}

android {
    namespace = "com.composetest.core.designsystem"
}

dependencies {
    includeModules(
        AppModule.CORE_UI,
        AppModule.COMMON_UTILITY
    )
}