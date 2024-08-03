plugins {
    alias(libs.plugins.openWeather.library)
    alias(libs.plugins.openWeather.compose)
    alias(libs.plugins.kotlinSerialization)
    id("kotlin-parcelize")
}

android {
    namespace = "com.openweather.core.router"
}

dependencies {
    implementation(projects.common)
    implementation(libs.kotlin.json.serializable)
    implementation(libs.kotlin.reflect)
}