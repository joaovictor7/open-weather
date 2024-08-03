package com.openweather.feature.home.ui.home3

import com.openweather.core.ui.bases.BaseViewModel
import com.openweather.core.domain.usecases.AnalyticsUseCase
import com.openweather.core.router.extensions.getParam
import com.openweather.core.router.destinations.home.Home2Destination
import com.openweather.core.router.managers.NavigationManager
import com.openweather.feature.home.ui.home3.analytics.Home3Analytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class Home3ViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    override val analyticsUseCase: AnalyticsUseCase
) : BaseViewModel<Home3UiState>(Home3Analytic, Home3UiState()), Home3CommandReceiver {

    override val commandReceiver = this

    init {
        openScreenAnalytic()
        val e = navigationManager.getParam<Home2Destination>()
        updateUiState { it.copy(t = e.teste) }
    }

    override fun returnLogin() {
        navigationManager.navigateBack()
        count++
    }

    private companion object {
        var count = 1
    }
}