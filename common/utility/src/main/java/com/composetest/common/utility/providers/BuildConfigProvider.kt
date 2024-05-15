package com.composetest.common.utility.providers

import com.composetest.common.utility.domain.models.BuildConfigModel

interface BuildConfigProvider {
    val buildConfigModel: BuildConfigModel
}