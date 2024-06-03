plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.data"
}

dependencies {
    implementation(projects.core.utility)
    implementation(projects.core.database)
    implementation(libs.androidx.dataStore)
    implementation(libs.androidx.workManager)
    implementation(libs.firebase.auth)
    implementation(libs.room)
    implementation(libs.mockk)
}