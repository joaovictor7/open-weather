plugins {
    alias(libs.plugins.composeTestLibrary)
}

android {
    namespace = "com.composetest.core.test"
}

dependencies {
    implementation(libs.junit5)
    implementation(libs.kotlin.courotines.test)
}