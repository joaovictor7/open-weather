import appconfig.AppModules
import utils.includeModules

plugins {
    alias(libs.plugins.composeTestLibrary)
    alias(libs.plugins.composeTestCompose)
}

android {
    namespace = "com.composetest.core.ui"
}

dependencies {
    includeModules(AppModules.CORE)
}