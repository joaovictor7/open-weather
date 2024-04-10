package modularization

import appconfig.AppConfig.APP_NAME
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

internal fun BaseAppModuleExtension.setBuildTypes() {
    buildTypes {
        all {
            buildConfigField("Boolean", "DYNAMIC_COLORS", "true")
        }
        release {
            manifestPlaceholders["appName"] = APP_NAME
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            manifestPlaceholders["appName"] = "$APP_NAME - DEBUG"
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
            buildConfigField("Boolean", "DYNAMIC_COLORS", "false")
        }
    }
}