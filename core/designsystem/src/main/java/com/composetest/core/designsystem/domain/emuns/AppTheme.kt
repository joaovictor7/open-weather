package com.composetest.core.designsystem.domain.emuns

enum class AppTheme {
    AUTO,
    DARK,
    LIGHT;

    companion object {
        fun getAppTheme(appThemeName: String?) = entries.find { it.name == appThemeName } ?: AUTO
    }
}