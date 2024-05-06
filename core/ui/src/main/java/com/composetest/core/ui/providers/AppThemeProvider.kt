package com.composetest.core.ui.providers

import com.composetest.core.ui.domain.enums.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppThemeProvider @Inject constructor() {

    private val _themeState = MutableStateFlow(AppTheme.AUTO)
    val themeState = _themeState.asStateFlow()

}