package com.composetest.feature.home.ui.home

import androidx.lifecycle.viewModelScope
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.common.di.qualifiers.IoDispatcher
import com.composetest.common.enums.Theme
import com.composetest.core.domain.usecases.analytics.AnalyticsUseCase
import com.composetest.core.domain.usecases.apptheme.SetAppThemeUseCase
import com.composetest.core.router.extensions.getParam
import com.composetest.core.router.destinations.home.Home2Destination
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.router.providers.NavigationProvider
import com.composetest.feature.home.ui.home.analytics.HomeAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider,
    private val setAppThemeUseCase: SetAppThemeUseCase,
    override val analyticsUseCase: AnalyticsUseCase,
    @IoDispatcher override val dispatcher: CoroutineDispatcher
) : BaseViewModel<HomeUiState>(HomeAnalytic(), HomeUiState()), HomeCommandReceiver {

    init {
        openScreenAnalytic()
        val e = navigationProvider.getParam<HomeDestination>()
        updateUiState { it.copy(t = e.innerHome.teste) }
    }

    override fun navigateToHome2() {
        navigationProvider.navigate(Home2Destination("tessfdf", "rer"))
    }

    override fun changeAppTheme(
        theme: Theme?,
        dynamicColors: Boolean?
    ) {
        viewModelScope.launch {
            when {
                theme != null -> setAppThemeUseCase.invoke(theme)
                dynamicColors != null -> setAppThemeUseCase.invoke(dynamicColors)
            }
        }
    }
}