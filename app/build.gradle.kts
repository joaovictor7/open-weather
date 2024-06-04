plugins {
    alias(libs.plugins.composeTest.application)
    alias(libs.plugins.composeTest.compose)
    alias(libs.plugins.composeTest.test)
}

android {
    val appPackage = "com.composetest"
    namespace = appPackage
    defaultConfig {
        applicationId = appPackage
    }
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.utility)
    implementation(projects.core.router)
    implementation(projects.core.domain)
    implementation(projects.feature.login)
    implementation(projects.feature.home)
}