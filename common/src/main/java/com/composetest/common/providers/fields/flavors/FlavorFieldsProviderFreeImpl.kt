package com.composetest.common.providers.fields.flavors

import com.composetest.common.models.FlavorFieldsModel

internal class FlavorFieldsProviderFreeImpl : FlavorFieldsProvider {
    override val get = FlavorFieldsModel(
        none = String()
    )
}