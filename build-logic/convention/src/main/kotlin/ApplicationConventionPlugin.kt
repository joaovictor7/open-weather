import appconfig.AppConfig
import appconfig.AppModules
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import modularization.configureAndroid
import modularization.setBuildTypes
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import utils.includeModules

internal class ApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("com.google.dagger.hilt.android")
            }
            extensions.configure<BaseAppModuleExtension> {
                configureAndroid(this@with, this)
                defaultConfig {
                    versionCode = AppConfig.CODE_VERSION
                    versionName = AppConfig.NAME_VERSION
                    targetSdk = AppConfig.TARGET_SDK_VERSION
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }
                signingConfigs {
                    create("release") {
                        storeFile = file(property("key.store").toString())
                        storePassword = property("key.store.password").toString()
                        keyAlias = property("key.alias").toString()
                        keyPassword = property("key.alias.password").toString()
                    }
                }
                buildFeatures {
                    buildConfig = true
                }
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
                setBuildTypes()
                dependencies {
                    includeModules(*AppModules.values())
                }
            }
        }
    }
}