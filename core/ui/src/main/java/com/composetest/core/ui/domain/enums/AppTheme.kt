package com.composetest.core.ui.domain.enums

enum class AppTheme {
    AUTO,
    DARK,
    LIGHT;

    companion object {
        fun getAppTheme(appThemeName: String?) = entries.find { it.name == appThemeName } ?: AUTO
    }
}