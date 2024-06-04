plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.database"
}

dependencies {
    implementation(projects.core.security)
    implementation(projects.core.utility)
    implementation(libs.room)
    implementation(libs.sqlCipher)
    ksp(libs.roomCompile)
}