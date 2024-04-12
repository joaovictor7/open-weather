package com.composetest.core.models.responses.bases

open class BaseResponse(
    val statusCode: Int = 200,
    val errorMsg: String? = null,
    val uiErrorMsg: String? = null
)
