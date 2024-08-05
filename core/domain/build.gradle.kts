plugins {
    alias(libs.plugins.openWeather.library)
    alias(libs.plugins.openWeather.test)
}

android {
    namespace = "com.openweather.core.domain"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.data)
    implementation(projects.core.database)
}