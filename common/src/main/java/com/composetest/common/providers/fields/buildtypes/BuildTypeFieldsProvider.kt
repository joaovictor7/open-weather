package com.composetest.common.providers.fields.buildtypes

import com.composetest.common.models.BuildTypeFieldsModel

interface BuildTypeFieldsProvider {
    val get: BuildTypeFieldsModel get() = BuildTypeFieldsModel(
        baseApiUrl = "http://10.0.2.2/", // Emulator local Host
//        baseApiUrl = "http://192.168.1.15/", // Macbook local Host
        baseApiPort = 8080
    )
}