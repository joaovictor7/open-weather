package com.composetest.router.destinations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Destinations(val destination: Destination)

enum class Destination {
    LOGIN,
    HOME
}