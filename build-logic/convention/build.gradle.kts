plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.tools.build.gradle)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.kotlin.compose.gradle.plugin)
}

gradlePlugin {
    plugins {
        fun registerPlugin(id: String, className: String) {
            register(id) {
                this.id = id
                this.implementationClass = className
            }
        }
        registerPlugin(
            id = "com.openweather.application",
            className = "ApplicationConventionPlugin"
        )
        registerPlugin(
            id = "com.openweather.library",
            className = "LibraryConventionPlugin"
        )
        registerPlugin(
            id = "com.openweather.compose",
            className = "ComposeConventionPlugin"
        )
        registerPlugin(
            id = "com.openweather.test",
            className = "TestConventionPlugin"
        )
    }
}