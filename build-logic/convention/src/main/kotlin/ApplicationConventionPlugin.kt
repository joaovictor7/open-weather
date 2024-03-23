import appconfig.AppConfig
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import modularization.configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure



internal class ApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            extensions.configure<BaseAppModuleExtension> {
                configureAndroid(this@with)
                defaultConfig {
                    versionCode = AppConfig.CODE_VERSION
                    versionName = AppConfig.NAME_VERSION
                    targetSdk = AppConfig.TARGET_SDK_VERSION
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
            }
        }
    }
}