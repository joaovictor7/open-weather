package com.openweather.core.data.providers

import com.openweather.common.providers.BuildConfigProvider
import javax.inject.Inject

internal class FakeInstanceProviderImpl @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider
) : FakeInstanceProvider {

    override fun <Instance> getInstance(
        instance: Instance,
        fakeInstance: Instance
    ) = if (buildConfigProvider.get.isDebug) fakeInstance else instance
}