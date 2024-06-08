package com.composetest.core.domain.converters

import com.composetest.core.domain.models.AppThemeModel
import com.composetest.core.domain.enums.Theme
import com.composetest.core.utility.extensions.orFalse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AppThemeModelConverter @Inject constructor() {

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