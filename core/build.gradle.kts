plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.compose)
    alias(libs.plugins.composeTest.test)
}

android {
    namespace = "com.composetest.core"
}