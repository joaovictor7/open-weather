package com.composetest.core.designsystem.domain.bases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event, State : BaseState>(stateInstance: State) : ViewModel() {

    private val _state = MutableStateFlow(stateInstance)
    val state = _state.asStateFlow()

    abstract fun handleEvent(event: Event)

    protected fun updateState(onNewState: (State) -> State) {
        _state.update(onNewState)
    }

    protected fun <T> asyncFlowTask(
        flowTask: Flow<T>,
        onSuccess: (param: T) -> Unit,
        onError: ((e: Throwable) -> Unit)? = null,
        onStart: (() -> Unit)? = null,
        onCompletion: (() -> Unit)? = null
    ) {
        viewModelScope.launch {
            flowTask
                .onStart { onStart?.invoke() }
                .onCompletion { onCompletion?.invoke() }
                .catch {
                    onError?.invoke(it)
                }.collect {
                    onSuccess.invoke(it)
                }
        }
    }
}