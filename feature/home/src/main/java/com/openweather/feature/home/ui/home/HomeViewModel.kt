package com.openweather.feature.home.ui.home

import com.openweather.core.ui.bases.BaseViewModel
import com.openweather.common.enums.Theme
import com.openweather.core.domain.managers.AppThemeManager
import com.openweather.core.domain.usecases.AnalyticsUseCase
import com.openweather.core.router.extensions.getParam
import com.openweather.core.router.destinations.home.Home2Destination
import com.openweather.core.router.destinations.home.HomeDestination
import com.openweather.core.router.extensions.getResultFlow
import com.openweather.core.router.managers.NavigationManager
import com.openweather.core.router.providers.NavHostControllerProvider
import com.openweather.core.router.results.home.Home2Result
import com.openweather.feature.home.ui.home.analytics.HomeAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val navHostControllerProvider: NavHostControllerProvider,
    private val appThemeManager: AppThemeManager,
    override val analyticsUseCase: AnalyticsUseCase
) : BaseViewModel<HomeUiState>(HomeAnalytic, HomeUiState()), HomeCommandReceiver {

    override val commandReceiver = this

    init {
        openScreenAnalytic()
        val e = navigationManager.getParam<HomeDestination>()
        updateUiState { it.copy(t = e.innerHome.teste) }
        teste()
    }

    override fun navigateToHome2() {
        navigationManager.navigate(Home2Destination("tessfdf", "rer"))
    }

    override fun changeAppTheme(
        theme: Theme?,
        dynamicColors: Boolean?
    ) {
        runAsyncTask {
            when {
                theme != null -> appThemeManager.setTheme(theme)
                dynamicColors != null -> appThemeManager.setDynamicColor(dynamicColors)
            }
        }
    }

    private fun teste() {
        runFlowTask(flow = navHostControllerProvider.getResultFlow<Home2Result>()) {
            val e = it
        }
    }
}