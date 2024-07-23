package com.composetest.common.di.qualifiers

import com.composetest.common.enums.Dispatchers
import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Dispatcher(val dispatcher: Dispatchers)