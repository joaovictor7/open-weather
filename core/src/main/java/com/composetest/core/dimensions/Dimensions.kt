package com.composetest.core.dimensions

import androidx.compose.runtime.Composable
import com.composetest.core.dimensions.densities.defaults.Component
import com.composetest.core.dimensions.densities.defaults.FontSize
import com.composetest.core.dimensions.densities.defaults.Spacing
import com.composetest.core.dimensions.densities.sw360.ComponentSw360
import com.composetest.core.dimensions.densities.sw600.ComponentSw600
import com.composetest.core.dimensions.densities.sw600.FontSizeSw600
import com.composetest.core.dimensions.densities.sw600.SpacingSw600
import com.composetest.core.enums.DensitiesEnum.Companion.getDensity

val spacings: Spacing
    @Composable get() = getDensity(
        default = Spacing(),
        sw600 = SpacingSw600(),
    )

val fontSizes: FontSize
    @Composable get() = getDensity(
        default = FontSize(),
        sw600 = FontSizeSw600(),
    )

val components: Component
    @Composable get() = getDensity(
        default = Component(),
        sw360 = ComponentSw360(),
        sw600 = ComponentSw600()
    )