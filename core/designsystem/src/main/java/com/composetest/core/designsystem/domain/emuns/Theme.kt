package com.composetest.core.designsystem.domain.emuns

enum class Theme {
    AUTO,
    DARK,
    LIGHT;

    companion object {
        fun getThemeByName(themeName: String?) = entries.find { it.name == themeName } ?: AUTO
    }
}