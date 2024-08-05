package modularization

import appconfig.AppBuildType
import appconfig.AppFlavor
import com.android.build.api.dsl.ApplicationBaseFlavor
import com.android.build.api.dsl.ApplicationBuildType

internal fun ApplicationBuildType.setBuildConfigFields(buildType: AppBuildType) {
    buildConfigField("String", "OPEN_WEATHER_KEY", "\"8fbe2064ecb71fc9bc7d9bb6a4244818\"")
    buildConfigField("String", "OPEN_WEATHER_API_HOST", "\"api.openweathermap.org/data/2.5\"")
}

internal fun ApplicationBaseFlavor.setBuildConfigFields(flavor: AppFlavor) {}