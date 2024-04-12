package com.composetest.ui

data class MainState(
    val darkTheme: Boolean = false,
    val dynamicColor: Boolean = false
) {
    fun setTheme(darkTheme: Boolean, dynamicColor: Boolean) = copy(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    )
}
