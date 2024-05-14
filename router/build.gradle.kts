import appconfig.AppModules
import utils.includeModules

plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.compose)
}

android {
    namespace = "com.composetest.router"
}

dependencies {
    includeModules(AppModules.CORE)
}