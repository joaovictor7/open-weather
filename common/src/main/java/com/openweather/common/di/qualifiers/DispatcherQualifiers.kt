package com.openweather.common.di.qualifiers

import com.openweather.common.enums.Dispatchers
import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Dispatcher(val dispatcher: Dispatchers)