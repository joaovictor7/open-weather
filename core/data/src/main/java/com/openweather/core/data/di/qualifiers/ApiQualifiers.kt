package com.openweather.core.data.di.qualifiers

import com.openweather.core.data.enums.NetworkApi
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class Api(val networkApi: NetworkApi)