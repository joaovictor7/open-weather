package com.openweather.providers

import android.os.Build
import com.openweather.BuildConfig
import com.openweather.common.enums.BuildType.Companion.getBuildType
import com.openweather.common.enums.Flavor.Companion.getFlavor
import com.openweather.common.models.BuildConfigModel
import com.openweather.common.providers.BuildConfigProvider
import javax.inject.Inject

internal class BuildConfigProviderImpl @Inject constructor() : BuildConfigProvider {
    override val get = BuildConfigModel(
        applicationId = BuildConfig.APPLICATION_ID,
        versionName = BuildConfig.VERSION_NAME,
        versionCode = BuildConfig.VERSION_CODE,
        buildType = BuildConfig.BUILD_TYPE.getBuildType(),
        flavor = BuildConfig.FLAVOR.getFlavor(),
        androidSdkVersion = Build.VERSION.SDK_INT
    )
}