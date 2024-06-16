package com.composetest.feature.home.ui.home

import androidx.lifecycle.viewModelScope
import com.composetest.common.abstracts.BaseViewModel
import com.composetest.common.di.qualifiers.IoDispatcher
import com.composetest.common.enums.Theme
import com.composetest.core.domain.usecases.apptheme.SetDynamicColorsUseCase
import com.composetest.core.domain.usecases.apptheme.SetThemeUseCase
import com.composetest.core.router.extensions.getParam
import com.composetest.core.router.destinations.home.Home2Destination
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider,
    private val setThemeUseCase: SetThemeUseCase,
    private val setDynamicColorsUseCase: SetDynamicColorsUseCase,
    @IoDispatcher override val dispatcher: CoroutineDispatcher
) : BaseViewModel<HomeUiState>(HomeUiState()), HomeCommandReceiver {

    init {
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
                theme != null -> setThemeUseCase(theme)
                dynamicColors != null -> setDynamicColorsUseCase(dynamicColors)
            }
        }
    }
}