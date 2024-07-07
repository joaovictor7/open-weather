package com.composetest.common.providers.fields.flavors

import com.composetest.common.models.FlavorFieldsModel

internal class FlavorFieldsProviderFullImpl : FlavorFieldsProvider {
    override val get = FlavorFieldsModel(
        none = String()
    )
}