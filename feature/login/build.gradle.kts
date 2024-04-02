import appconfig.AppModule
import utils.includeModule

plugins {
    alias(libs.plugins.composeTestLibrary)
    alias(libs.plugins.composeTestCompose)
    alias(libs.plugins.composeTestTest)
}

android {
    namespace = "com.composetest.feature.login"
}

dependencies {
    includeModule(AppModule.CORE)
}