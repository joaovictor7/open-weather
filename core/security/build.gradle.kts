import appconfig.AppModule
import utils.includeModules

plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.security"
}

dependencies {
    implementation(libs.androidx.securityCrypto)
    implementation(libs.sqlCipher)
    implementation(libs.sqLite)
    includeModules(AppModule.CORE_UTILITY)
}