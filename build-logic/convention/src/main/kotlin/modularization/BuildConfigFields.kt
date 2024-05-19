package modularization

import appconfig.AppBuildType
import appconfig.AppFlavor
import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationProductFlavor

internal fun ApplicationBuildType.setBuildConfigFields(buildType: AppBuildType) {
    when (buildType) {
        else -> Unit
    }
}

internal fun ApplicationProductFlavor.setBuildConfigFields(flavor: AppFlavor) {
    when (flavor) {
        else -> Unit
    }
}