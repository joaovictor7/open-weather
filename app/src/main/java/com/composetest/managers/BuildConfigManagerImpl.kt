package com.composetest.managers

import com.composetest.BuildConfig
import com.composetest.core.domain.models.BuildConfigModel
import com.composetest.core.managers.BuildConfigManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BuildConfigManagerImpl @Inject constructor() : BuildConfigManager {

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