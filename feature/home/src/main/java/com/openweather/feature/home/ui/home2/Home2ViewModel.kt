package com.openweather.feature.home.ui.home2

import com.openweather.core.ui.bases.BaseViewModel
import com.openweather.core.domain.usecases.AnalyticsUseCase
import com.openweather.core.router.extensions.getParam
import com.openweather.core.router.destinations.home.Home2Destination
import com.openweather.core.router.destinations.home.Home3Destination
import com.openweather.core.router.enums.NavigationMode
import com.openweather.core.router.managers.NavigationManager
import com.openweather.feature.home.ui.home2.analytics.Home2Analytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class Home2ViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    override val analyticsUseCase: AnalyticsUseCase
) : BaseViewModel<Home2UiState>(Home2Analytic, Home2UiState()), Home2CommandReceiver {

    override val commandReceiver = this

    init {
        openScreenAnalytic()
        val e = navigationManager.getParam<Home2Destination>()
        updateUiState { it.copy(t = e.teste) }
    }

    override fun returnHome() {
        navigationManager.navigate(
            Home3Destination("teste", "teste"),
            NavigationMode.REMOVE_CURRENT_SCREEN
        )
        count++
    }

    private companion object {
        var count = 1
    }
}