package com.composetest.core.test.interfaces

import com.composetest.core.test.junitextensions.CoroutineExtensionContext
import kotlinx.coroutines.test.TestDispatcher
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutineExtensionContext::class)
interface CoroutineTest {
    var testDispatcher: TestDispatcher
}