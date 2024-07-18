plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.database"
}

dependencies {
    implementation(projects.core.security)
    implementation(projects.core.data)
    implementation(projects.common)
    implementation(libs.room)
    ksp(libs.roomCompile)
}