package com.composetest.router.navigation.qualifiers

import com.composetest.router.domain.enums.Destinations
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Destination(val destination: Destinations)