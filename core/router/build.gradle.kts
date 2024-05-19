import appconfig.AppModule
import utils.includeModules

plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.compose)
}

android {
    namespace = "com.composetest.core.router"
}

dependencies {
    includeModules(AppModule.CORE_UTILITY)
}