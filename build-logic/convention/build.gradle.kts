plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.tools.build.gradle)
    implementation(libs.kotlin.gradle.plugin)
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
            id = "com.composetest.application",
            className = "ApplicationConventionPlugin"
        )
        registerPlugin(
            id = "com.composetest.library",
            className = "LibraryConventionPlugin"
        )
        registerPlugin(
            id = "com.composetest.compose",
            className = "ComposeConventionPlugin"
        )
        registerPlugin(
            id = "com.composetest.test",
            className = "TestConventionPlugin"
        )
    }
}