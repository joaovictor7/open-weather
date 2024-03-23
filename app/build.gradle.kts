plugins {
    alias(libs.plugins.composeTestApplication)
    alias(libs.plugins.composeTestCompose)
    alias(libs.plugins.composeTestTest)
}

android {
    namespace = "com.composetest"

    defaultConfig {
        applicationId = "com.composetest"
    }
}