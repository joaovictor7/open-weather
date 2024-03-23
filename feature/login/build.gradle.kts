plugins {
    alias(libs.plugins.composeTestLibrary)
    alias(libs.plugins.composeTestCompose)
}

android {
    namespace = "com.composetest.feature.login"
}