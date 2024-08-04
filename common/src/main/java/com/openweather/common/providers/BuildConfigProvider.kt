package com.openweather.common.providers

import com.openweather.common.models.BuildConfigModel

interface BuildConfigProvider {
    val get: BuildConfigModel
}