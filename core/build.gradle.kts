plugins {
    alias(libs.plugins.composeTestLibrary)
    alias(libs.plugins.composeTestCompose)
    alias(libs.plugins.composeTestTest)
}

android {
    namespace = "com.composetest.core"
}