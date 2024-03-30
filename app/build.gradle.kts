plugins {
    alias(libs.plugins.composeTestApplication)
    alias(libs.plugins.composeTestCompose)
    alias(libs.plugins.composeTestTest)
}

android {
    val appPackage = "com.composetest"
    namespace = appPackage
    defaultConfig {
        applicationId = appPackage
    }
}

dependencies {
    implementation(project(":core"))
}