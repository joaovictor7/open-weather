import appconfig.AppModule
import utils.includeModules

plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.data"
}

dependencies {
    includeModules(AppModule.CORE_UTILITY)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.dataStore)
}