package com.composetest.common.providers

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