package com.composetest.core.data.providers

import com.composetest.common.providers.BuildConfigProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FakeInstanceProviderImpl @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider
) : FakeInstanceProvider {

    override fun <Instance> getInstance(
        instance: Instance,
        fakeInstance: Instance
    ) = if (buildConfigProvider.get.isDebug) fakeInstance else instance
}