package com.composetest.core.utility.providers

import com.composetest.core.utility.domain.models.BuildConfigFieldsModel

interface BuildConfigProvider {
    val get: BuildConfigFieldsModel
}