package com.openweather.core.designsystem.dimensions

import androidx.compose.runtime.Composable
import com.openweather.core.designsystem.dimensions.densities.Densities
import com.openweather.core.designsystem.dimensions.densities.defaults.Component
import com.openweather.core.designsystem.dimensions.densities.defaults.FontSize
import com.openweather.core.designsystem.dimensions.densities.defaults.Spacing
import com.openweather.core.designsystem.dimensions.densities.sw360.ComponentSw360
import com.openweather.core.designsystem.dimensions.densities.sw600.ComponentSw600
import com.openweather.core.designsystem.dimensions.densities.sw600.FontSizeSw600
import com.openweather.core.designsystem.dimensions.densities.sw600.SpacingSw600

val spacings: Spacing
    @Composable get() = Densities.getDensity(
        default = Spacing(),
        sw600 = SpacingSw600(),
    )

val fontSizes: FontSize
    @Composable get() = Densities.getDensity(
        default = FontSize(),
        sw600 = FontSizeSw600(),
    )

val components: Component
    @Composable get() = Densities.getDensity(
        default = Component(),
        sw360 = ComponentSw360(),
        sw600 = ComponentSw600()
    )