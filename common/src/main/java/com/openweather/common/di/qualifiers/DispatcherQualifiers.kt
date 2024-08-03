package com.openweather.common.di.qualifiers

import com.openweather.common.enums.Dispatchers
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Dispatcher(val dispatcher: Dispatchers)