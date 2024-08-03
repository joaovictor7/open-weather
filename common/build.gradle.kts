plugins {
    alias(libs.plugins.openWeather.library)
    alias(libs.plugins.openWeather.compose)
}

android {
    namespace = "com.openweather.common"
}