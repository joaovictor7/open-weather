plugins {
    alias(libs.plugins.openWeather.library)
}

android {
    namespace = "com.openweather.core.database"
}

dependencies {
    implementation(projects.core.security)
    implementation(projects.common)
    implementation(libs.room)
    ksp(libs.roomCompile)
}