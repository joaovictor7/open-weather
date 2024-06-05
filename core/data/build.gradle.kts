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
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.negotiation)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
}