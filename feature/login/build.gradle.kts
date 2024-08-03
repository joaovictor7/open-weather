plugins {
    alias(libs.plugins.openWeather.library)
    alias(libs.plugins.openWeather.compose)
    alias(libs.plugins.openWeather.test)
}

android {
    namespace = "com.openweather.feature.login"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.router)
    implementation(projects.core.domain)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
}