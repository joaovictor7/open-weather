package com.composetest.core.ui.bases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composetest.common.analytics.interfaces.Analytic
import com.composetest.common.analytics.ErrorAnalyticEvent
import com.composetest.common.analytics.OpenScreenAnalyticEvent
import com.composetest.core.domain.usecases.AnalyticsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState>(
    private val analytic: Analytic,
    uiState: UiState
) : ViewModel() {

    abstract val dispatcher: CoroutineDispatcher
    abstract val analyticsUseCase: AnalyticsUseCase

    private val _uiState = MutableStateFlow(uiState)
    val uiState = _uiState.asStateFlow()

    protected fun updateUiState(onNewUiState: (UiState) -> UiState) {
        _uiState.update(onNewUiState)
    }

    protected fun openScreenAnalytic() {
        viewModelScope.launch(dispatcher) {
            analyticsUseCase(OpenScreenAnalyticEvent(analytic))
        }
    }

    protected fun <T> collectFlow(
        flow: Flow<T>,
        onError: (suspend (e: Throwable) -> Unit)? = null,
        onStart: (suspend () -> Unit)? = null,
        onCompletion: (suspend () -> Unit)? = null,
        onCollect: suspend (param: T) -> Unit
    ) {
        viewModelScope.launch {
            flow.flowOn(dispatcher)
                .onStart { onStart?.invoke() }
                .onCompletion { onCompletion?.invoke() }
                .catch {
                    analyticsUseCase(ErrorAnalyticEvent(it, analytic))
                    onError?.invoke(it)
                }
                .collect { onCollect(it) }
        }
    }
}