package com.openweather.common.providers.fields.buildtypes

import com.openweather.common.models.BuildTypeFieldsModel

interface BuildTypeFieldsProvider {
    val get: BuildTypeFieldsModel get() = BuildTypeFieldsModel()
}