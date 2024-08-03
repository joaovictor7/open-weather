plugins {
    alias(libs.plugins.openWeather.application)
    alias(libs.plugins.openWeather.compose)
    alias(libs.plugins.openWeather.test)
}

android {
    val appPackage = "com.openweather"
    namespace = appPackage
    defaultConfig {
        applicationId = appPackage
    }
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    implementation(projects.core.router)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.feature.login)
    implementation(projects.feature.home)
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.splashScreen)
}