package com.composetest.core.domain.models.enums

enum class Theme {
    AUTO,
    DARK,
    LIGHT;

    companion object {
        fun getThemeByName(themeName: String?) = entries.find { it.name == themeName } ?: AUTO
    }
}