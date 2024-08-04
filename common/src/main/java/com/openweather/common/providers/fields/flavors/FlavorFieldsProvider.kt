package com.openweather.common.providers.fields.flavors

import com.openweather.common.models.FlavorFieldsModel

interface FlavorFieldsProvider {
    val get: FlavorFieldsModel get() = FlavorFieldsModel()
}