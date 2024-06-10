package com.composetest.core.domain.converters

import com.composetest.common.models.AppThemeModel
import com.composetest.common.enums.Theme
import com.composetest.common.extensions.orFalse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppThemeModelConverter @Inject constructor() {

    operator fun invoke(
        theme: String?,
        dynamicColor: Boolean?,
        customTheme: Theme?
    ) = AppThemeModel(
        theme = Theme.getThemeByName(theme),
        dynamicColors = dynamicColor.orFalse,
        customTheme = customTheme
    )
}