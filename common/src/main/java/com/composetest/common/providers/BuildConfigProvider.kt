package com.composetest.common.providers

import com.composetest.common.models.BuildConfigModel

interface BuildConfigProvider {
    val get: BuildConfigModel
}