package com.composetest.core.providers

import com.composetest.core.models.BuildConfigModel

interface BuildConfigProvider {
    val buildConfigModel: BuildConfigModel
}