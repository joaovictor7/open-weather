package com.composetest.common.providers.fields.buildtypes

import com.composetest.common.models.BuildTypeFieldsModel

interface BuildTypeFieldsProvider {
    val get: BuildTypeFieldsModel get() = BuildTypeFieldsModel(
        baseApiUrl = "http://localhost/",
        baseApiPort = 8080
    )
}