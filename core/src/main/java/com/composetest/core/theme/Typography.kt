package com.composetest.core.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val defaultTextStyle = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal
)

fun getTypography(colorScheme: ColorScheme) = Typography(
    titleLarge = defaultTextStyle.copy(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        color = colorScheme.onSurface
    ),
    titleMedium = defaultTextStyle.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
        color = colorScheme.onSurface
    ),
    titleSmall = defaultTextStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        color = colorScheme.onSurface
    ),
    bodyLarge = defaultTextStyle.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = colorScheme.onSurfaceVariant
    ),
    bodyMedium = defaultTextStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        color = colorScheme.onSurfaceVariant
    ),
    bodySmall = defaultTextStyle.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
        color = colorScheme.onSurfaceVariant
    ),
    headlineLarge = defaultTextStyle.copy(
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    headlineMedium = defaultTextStyle.copy(
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    headlineSmall = defaultTextStyle.copy(
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    labelLarge = defaultTextStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = defaultTextStyle.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = defaultTextStyle.copy(
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = defaultTextStyle.copy(
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = defaultTextStyle.copy(
        fontSize = 45.sp,
        lineHeight = 52.sp
    ),
    displaySmall = defaultTextStyle.copy(
        fontSize = 36.sp,
        lineHeight = 44.sp
    )
)