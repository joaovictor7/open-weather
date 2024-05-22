package com.composetest.core.designsystem.domain.bases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event, State : BaseState>(stateInstance: State) : ViewModel() {

    private val _state = MutableStateFlow(stateInstance)
    val state = _state.asStateFlow()

    protected fun updateState(onNewState: (State) -> State) {
        _state.update(onNewState)
    }

    abstract fun handleEvent(event: Event)

    protected fun <T> lazyFlowTask(
        flowTask: Flow<T>,
        onError: ((e: Throwable) -> Unit)? = null,
        onSuccess: (param: T) -> Unit,
    ) {
        viewModelScope.launch {
            flowTask.catch {
                onError?.invoke(it)
            }.collect {
                onSuccess.invoke(it)
            }
        }
    }
}