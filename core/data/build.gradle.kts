plugins {
    alias(libs.plugins.openWeather.library)
    alias(libs.plugins.openWeather.test)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.openweather.core.data"
}

dependencies {
    implementation(projects.core.database)
    implementation(projects.common)
    implementation(libs.kotlin.json.serializable)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.negotiation)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.slf4j.api)
    ksp(libs.androidx.hilt.compiler)
}