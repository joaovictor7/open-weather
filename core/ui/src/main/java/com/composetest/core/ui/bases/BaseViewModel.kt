package com.composetest.core.ui.bases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composetest.common.analytics.ErrorAnalyticEvent
import com.composetest.common.analytics.OpenScreenAnalyticEvent
import com.composetest.common.analytics.interfaces.AnalyticScreen
import com.composetest.core.domain.usecases.AnalyticsUseCase
import com.composetest.core.ui.interfaces.BaseUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState : BaseUiState>(
    private val analyticScreen: AnalyticScreen,
    uiState: UiState
) : ViewModel() {

    abstract val analyticsUseCase: AnalyticsUseCase

    private val _uiState = MutableStateFlow(uiState)
    val uiState = _uiState.asStateFlow()

    protected fun updateUiState(onNewUiState: (UiState) -> UiState) {
        _uiState.update(onNewUiState)
    }

    protected fun openScreenAnalytic() {
        runAsyncTask {
            analyticsUseCase(OpenScreenAnalyticEvent(analyticScreen))
        }
    }

    protected fun <T> runFlowTask(
        flow: Flow<T>,
        onError: (suspend (e: Throwable) -> Unit)? = null,
        onStart: (suspend () -> Unit)? = null,
        onCompletion: (suspend () -> Unit)? = null,
        onCollect: suspend (param: T) -> Unit
    ) {
        viewModelScope.launch {
            flow.onStart { onStart?.invoke() }
                .onCompletion { onCompletion?.invoke() }
                .catch {
                    analyticsUseCase(ErrorAnalyticEvent(it, analyticScreen))
                    onError?.invoke(it)
                }
                .collect { onCollect(it) }
        }
    }

    protected fun runAsyncTask(
        onError: (suspend (Throwable) -> Unit)? = null,
        onStart: (suspend () -> Unit)? = null,
        onCompletion: (suspend () -> Unit)? = null,
        onAsyncTask: suspend () -> Unit
    ) {
        viewModelScope.launch {
            onStart?.invoke()
            runCatching {
                onAsyncTask()
            }.onFailure {
                analyticsUseCase(ErrorAnalyticEvent(it, analyticScreen))
                onError?.invoke(it)
            }
            onCompletion?.invoke()
        }
    }
}