plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.database"
}

dependencies {
    implementation(projects.core.security)
    implementation(projects.common)
    implementation(libs.sqlCipher)
    api(libs.room)
    ksp(libs.roomCompile)
}