package com.composetest.core.utility.extensions

val Boolean?.orTrue get() = this ?: true
val Boolean?.orFalse get() = this ?: false