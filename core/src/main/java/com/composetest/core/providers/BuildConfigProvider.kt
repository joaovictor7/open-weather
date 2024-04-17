package com.composetest.core.providers

import com.composetest.core.domain.models.BuildConfigModel

interface BuildConfigProvider {
    val buildConfigModel: BuildConfigModel
}