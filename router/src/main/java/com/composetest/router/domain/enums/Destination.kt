package com.composetest.router.domain.enums

enum class Destination {
    FEATURE_LOGIN,
    FEATURE_HOME;

    val route: String get() = name.lowercase()
}