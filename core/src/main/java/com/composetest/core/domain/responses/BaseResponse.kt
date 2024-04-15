package com.composetest.core.domain.responses

open class BaseResponse(
    val statusCode: Int = 200,
    val errorMsg: String? = null,
    val uiErrorMsg: String? = null
)
