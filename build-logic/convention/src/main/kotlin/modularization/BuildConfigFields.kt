package modularization

import appconfig.AppBuildType
import com.android.build.api.dsl.ApplicationBuildType

internal fun ApplicationBuildType.setBuildConfigFields(buildType: AppBuildType) {
    buildConfigField("String", "OPEN_WEATHER_KEY", "\"8fbe2064ecb71fc9bc7d9bb6a4244818\"")
}