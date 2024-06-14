package com.composetest.core.test.extensions

import com.composetest.core.test.models.ObserverStateFlowModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

fun <T> CoroutineScope.observerStateFlow(
    uiStateFlow: StateFlow<T>
): ObserverStateFlowModel<T> {
    val collectedStates = mutableListOf<T>()
    val job = launch {
        uiStateFlow.toList(collectedStates)
    }
    return ObserverStateFlowModel(job, collectedStates)
}