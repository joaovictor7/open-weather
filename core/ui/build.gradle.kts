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
    includeModules(
        AppModule.CORE_UTILITY,
        AppModule.CORE_DATA
    )
    implementation(libs.androidx.dataStore)
}