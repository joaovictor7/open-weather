package com.openweather.common.providers.fields.flavors

import com.openweather.common.models.FlavorFieldsModel

internal class FlavorFieldsProviderFreeImpl : FlavorFieldsProvider {
    override val get = FlavorFieldsModel(
        none = String()
    )
}