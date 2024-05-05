package com.composetest.router.domain.enums

enum class Destination {
    FEATURE_LOGIN,
    FEATURE_HOME,
    FEATURE_HOME2;

    val route: String get() = name.lowercase()
}