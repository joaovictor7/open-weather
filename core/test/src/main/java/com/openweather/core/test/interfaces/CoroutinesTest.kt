package com.openweather.core.test.interfaces

import com.openweather.core.test.extensions.CoroutinesExtension
import kotlinx.coroutines.test.TestDispatcher
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutinesExtension::class)
interface CoroutinesTest {
    var testDispatcher: TestDispatcher
}