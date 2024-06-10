package com.composetest.common.providers

import com.composetest.common.models.BuildConfigFieldsModel

interface BuildConfigProvider {
    val get: BuildConfigFieldsModel
}