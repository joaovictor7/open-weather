package com.composetest.core.designsystem.domain.converters

import com.composetest.core.designsystem.domain.emuns.Theme
import com.composetest.core.designsystem.domain.models.AppThemeModel
import com.composetest.core.utility.extensions.orFalse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppThemeModelConverter @Inject constructor() {
    fun convertTo(
        theme: String?,
        dynamicColor: Boolean?,
        customTheme: Theme?
    ) = AppThemeModel(
        theme = Theme.getThemeByName(theme),
        dynamicColors = dynamicColor.orFalse,
        customTheme = customTheme
    )
}