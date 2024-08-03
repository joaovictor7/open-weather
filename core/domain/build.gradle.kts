plugins {
    alias(libs.plugins.openWeather.library)
}

android {
    namespace = "com.openweather.core.domain"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.data)
    implementation(projects.core.database)
}