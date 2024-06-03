plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.security"
}

dependencies {
    implementation(projects.core.utility)
    implementation(libs.androidx.securityCrypto)
    implementation(libs.sqlCipher)
    implementation(libs.sqLite)
}