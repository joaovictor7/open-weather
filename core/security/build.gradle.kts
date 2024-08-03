plugins {
    alias(libs.plugins.openWeather.library)
}

android {
    namespace = "com.openweather.core.security"
}

dependencies {
    implementation(projects.common)
}