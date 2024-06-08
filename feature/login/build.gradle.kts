plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.compose)
    alias(libs.plugins.composeTest.test)
}

android {
    namespace = "com.composetest.feature.login"
}

dependencies {
    implementation(projects.core.router)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.utility)
    implementation(projects.core.designsystem)
}