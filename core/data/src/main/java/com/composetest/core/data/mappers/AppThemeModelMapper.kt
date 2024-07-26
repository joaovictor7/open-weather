package com.composetest.core.data.mappers

import com.composetest.common.models.AppThemeModel
import com.composetest.common.enums.Theme
import com.composetest.common.extensions.orFalse
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