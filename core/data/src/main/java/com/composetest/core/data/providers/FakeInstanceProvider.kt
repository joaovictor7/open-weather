package com.composetest.core.data.providers

internal interface FakeInstanceProvider {
    fun <Instance> getInstance(instance: Instance, fakeInstance: Instance): Instance
}