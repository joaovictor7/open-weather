package modularization

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

internal fun BaseAppModuleExtension.setBuildTypes() {
    buildTypes {
        all {
            buildConfigField("Boolean", "DYNAMIC_COLORS", "true")
        }
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
            buildConfigField("Boolean", "DYNAMIC_COLORS", "false")
        }
    }
}