plugins {
    alias(libs.plugins.openWeather.library)
    alias(libs.plugins.openWeather.compose)
}

android {
    namespace = "com.openweather.core.ui"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.designsystem)
    implementation(projects.common)
}