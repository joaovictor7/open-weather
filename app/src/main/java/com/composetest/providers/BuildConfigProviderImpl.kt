package com.composetest.providers

import com.composetest.BuildConfig
import com.composetest.core.models.BuildConfigModel
import com.composetest.core.providers.BuildConfigProvider
import javax.inject.Inject

class BuildConfigProviderImpl @Inject constructor() : BuildConfigProvider {

    override val buildConfigModel = BuildConfigModel(
        applicationId = BuildConfig.APPLICATION_ID,
        versionName = BuildConfig.VERSION_NAME,
        versionCode = BuildConfig.VERSION_CODE,
        buildType = BuildConfig.BUILD_TYPE,
        flavor = BuildConfig.FLAVOR,
        dynamicColors = BuildConfig.DYNAMIC_COLORS,
        useMock = BuildConfig.USE_MOCK
    )

}