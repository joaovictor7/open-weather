package com.composetest.common.throwables

class BadRequestThrowable(
    override val message: String
) : Throwable()