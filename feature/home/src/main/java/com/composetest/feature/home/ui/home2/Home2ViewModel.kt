package com.composetest.feature.home.ui.home2

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.common.di.qualifiers.IoDispatcher
import com.composetest.core.domain.usecases.AnalyticsUseCase
import com.composetest.core.router.extensions.getParam
import com.composetest.core.router.destinations.home.Home2Destination
import com.composetest.core.router.destinations.home.Home3Destination
import com.composetest.core.router.providers.NavigationProvider
import com.composetest.feature.home.ui.home2.analytics.Home2Analytic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
internal class Home2ViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider,
    override val analyticsUseCase: AnalyticsUseCase,
    @IoDispatcher override val dispatcher: CoroutineDispatcher
) : BaseViewModel<Home2UiState>(Home2Analytic(), Home2UiState()), Home2CommandReceiver {

    override val commandReceiver = this

    init {
        openScreenAnalytic()
        val e = navigationProvider.getParam<Home2Destination>()
        updateUiState { it.copy(t = e.teste) }
    }

    override fun returnHome() {
        navigationProvider.navigateRemovePrevious(Home3Destination("teste", "teste"))
        count++
    }

    private companion object {
        var count = 1
    }
}