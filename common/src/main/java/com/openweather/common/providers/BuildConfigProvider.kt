package com.openweather.common.providers

import com.openweather.common.models.buildconfig.BuildConfigModel

interface BuildConfigProvider {
    val get: BuildConfigModel
}