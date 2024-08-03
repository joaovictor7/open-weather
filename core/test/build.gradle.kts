plugins {
    alias(libs.plugins.openWeather.library)

}

android {
    namespace = "com.openweather.core.test"
}

dependencies {
    implementation(libs.junit5)
    implementation(libs.kotlin.coroutines.test)
}