// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.composeTestApplication) apply false
    alias(libs.plugins.composeTestLibrary) apply false
    alias(libs.plugins.composeTestCompose) apply false
    alias(libs.plugins.composeTestTest) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.googleServices) apply false
    alias(libs.plugins.firebaseCrashlytics) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.hilt) apply false
}