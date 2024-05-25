import appconfig.AppModule
import utils.includeModules

plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.data"
}

dependencies {
    implementation(libs.firebase.auth)
    implementation(libs.androidx.dataStore)
    implementation(libs.room)
    includeModules(
        AppModule.CORE_UTILITY,
        AppModule.CORE_DATABASE
    )
}