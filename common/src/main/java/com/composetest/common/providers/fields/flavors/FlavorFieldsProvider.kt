package com.composetest.common.providers.fields.flavors

import com.composetest.common.models.FlavorFieldsModel

interface FlavorFieldsProvider {
    val get: FlavorFieldsModel get() = FlavorFieldsModel(
        none = String()
    )
}