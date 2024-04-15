import appconfig.AppConfig
import appconfig.AppModules
import appconfig.AppSignings
import com.android.build.api.dsl.ApkSigningConfig
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import extensions.findLibrary
import extensions.implementation
import modularization.configureAndroid
import modularization.setBuildTypes
import modularization.setFlavors
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import utils.includeModules
import java.util.Properties

internal class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("com.google.dagger.hilt.android")
                apply("com.google.gms.google-services")
                apply("com.google.firebase.crashlytics")
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
                    createSigning(this@with, this, AppSignings.RELEASE)
                }
                buildFeatures {
                    buildConfig = true
                }
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1,LICENSE*}"
                    }
                }
                setBuildTypes()
                setFlavors()
            }
            dependencies {
                includeModules(*AppModules.values())
                implementation(findLibrary("firebase.analytics"))
                implementation(findLibrary("firebase.crashlytics"))
            }
        }
    }

    private fun createSigning(
        project: Project,
        apkSigningConfig: NamedDomainObjectContainer<out ApkSigningConfig>,
        name: AppSignings
    ) = with(project) {
        val propertyFile = file("../${name.signingName}-signing.properties")
        if (propertyFile.exists()) {
            val property = Properties().apply { propertyFile.inputStream().use { load(it) } }
            apkSigningConfig.create(name.signingName) {
                storeFile = file("../key/${name.signingName}/keystore.jks")
                storePassword = property.getProperty("key.store.password")
                keyAlias = property.getProperty("key.alias")
                keyPassword = property.getProperty("key.alias.password")
            }
        }
    }
}