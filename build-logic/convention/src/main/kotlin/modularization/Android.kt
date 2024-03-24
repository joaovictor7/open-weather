package modularization

import appconfig.AppConfig
import com.android.build.api.dsl.CommonExtension
import extensions.findLibrary
import extensions.implementation
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

internal fun configureAndroid(target: Project, commonExtension: CommonExtension<*, *, *, *, *, *>) {
    with(target) {
        pluginManager.apply("org.jetbrains.kotlin.android")
        tasks.withType<KotlinJvmCompile> {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_19)
                freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
            }
        }
        dependencies {
            implementation(findLibrary("androidx.core.ktx"))
            implementation(findLibrary("androidx.lifecycle.runtime.ktx"))
        }
    }
    commonExtension.apply {
        compileSdk = AppConfig.COMPILE_SDK_VERSION
        defaultConfig {
            minSdk = AppConfig.MIN_SDK_VERSION
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        setBuildTypes()
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_19
            targetCompatibility = JavaVersion.VERSION_19
        }
    }
}

private fun CommonExtension<*, *, *, *, *, *>.setBuildTypes() {
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}