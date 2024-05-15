import appconfig.AppModule
import utils.includeModules

plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.compose)
}

android {
    namespace = "com.composetest.core.ui"
}

dependencies {
    includeModules(AppModule.COMMON_UTILITY)
}