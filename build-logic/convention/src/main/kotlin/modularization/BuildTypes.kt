package modularization

import com.android.build.api.dsl.CommonExtension

internal fun CommonExtension<*, *, *, *, *, *>.setBuildTypes() {
    buildTypes {
        all {
            buildConfigField("Boolean", "DYNAMIC_COLORS", "true")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            buildConfigField("Boolean", "DYNAMIC_COLORS", "false")
        }
    }
}