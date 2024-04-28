package modularization

import appconfig.AppConfig
import com.android.build.api.dsl.CommonExtension
import extensions.findLibrary
import extensions.implementation
import extensions.kapt
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

internal fun configureAndroid(
    project: Project,
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    project.configure()
    commonExtension.configure()
}

private fun Project.configure() {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.android")
        apply("org.jetbrains.kotlin.plugin.serialization")
        apply("kotlin-parcelize")
        apply("kotlin-kapt")
    }
    tasks.withType<KotlinJvmCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_19)
        }
    }
    extensions.getByType<KaptExtension>().apply {
        correctErrorTypes = true
    }
    setAllModulesBuildTypes()
    dependencies {
        implementation(findLibrary("androidx.core.ktx"))
        implementation(findLibrary("androidx.lifecycle.runtime.ktx"))
        implementation(findLibrary("kotlin.json.serializable"))
        implementation(findLibrary("android.hilt"))
        implementation(platform(findLibrary("firebase.bom")))
        kapt(findLibrary("android.hilt.compiler"))
    }
}

private fun CommonExtension<*, *, *, *, *, *>.configure() {
    compileSdk = AppConfig.COMPILE_SDK_VERSION
    defaultConfig {
        minSdk = AppConfig.MIN_SDK_VERSION
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
}