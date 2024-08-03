package com.openweather.core.data.mappers

import com.openweather.common.models.AppThemeModel
import com.openweather.common.enums.Theme
import com.openweather.common.extensions.orFalse
import javax.inject.Inject

internal class AppThemeModelMapper @Inject constructor() {

    operator fun invoke(
        theme: String?,
        dynamicColor: Boolean?
    ) = AppThemeModel(
        theme = Theme.getThemeByName(theme),
        dynamicColors = dynamicColor.orFalse
    )
}