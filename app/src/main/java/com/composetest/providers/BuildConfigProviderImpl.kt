package com.composetest.providers

import android.os.Build
import com.composetest.BuildConfig
import com.composetest.common.models.BuildConfigFieldsModel
import com.composetest.common.providers.BuildConfigProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class BuildConfigProviderImpl @Inject constructor() : BuildConfigProvider {
    override val get = BuildConfigFieldsModel(
        applicationId = BuildConfig.APPLICATION_ID,
        versionName = BuildConfig.VERSION_NAME,
        versionCode = BuildConfig.VERSION_CODE,
        buildType = BuildConfig.BUILD_TYPE,
        flavor = BuildConfig.FLAVOR,
        androidSdkVersion = Build.VERSION.SDK_INT
    )
}