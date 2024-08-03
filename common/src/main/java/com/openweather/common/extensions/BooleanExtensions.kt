package com.openweather.common.extensions

val Boolean?.orTrue get() = this ?: true
val Boolean?.orFalse get() = this ?: false