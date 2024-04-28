package com.composetest.core.extensions

val Boolean?.orTrue get() = this ?: true
val Boolean?.orFalse get() = this ?: false