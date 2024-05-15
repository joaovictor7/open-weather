import appconfig.AppModule
import utils.includeModules

plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.data"
}

dependencies {
    includeModules(AppModule.COMMON_UTILITY)
    implementation(libs.firebase.auth)
}