package com.composetest.core.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Purple80 = Color(0xFFD0BCFF)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey80 = Color(0xFFCCC2DC)
val PurpleGrey40 = Color(0xFF625b71)
val Pink80 = Color(0xFFEFB8C8)
val Pink40 = Color(0xFF7D5260)
val Teal200 = Color(0xFF03DAC5)
val Grey200 = Color(0xFFEEEEEE)
val Grey400 = Color(0xFFBDBDBD)
val Grey900 = Color(0xFF212121)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

internal val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

internal val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)