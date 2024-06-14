package com.composetest.feature.login.viewmodels

import com.composetest.common.models.BuildConfigFieldsModel
import com.composetest.common.providers.BuildConfigProvider
import com.composetest.common.throwables.InvalidCredentialsThrowable
import com.composetest.common.throwables.RemoteNetworkThrowable
import com.composetest.core.designsystem.components.alertdialogs.enums.ErrorAlertDialog
import com.composetest.core.test.utils.CoroutineExtensionContext
import com.composetest.core.domain.usecases.AuthenticationUseCase
import com.composetest.core.test.extensions.observerStateFlow
import com.composetest.feature.login.ui.login.Login
import com.composetest.feature.login.ui.login.LoginUiState
import com.composetest.feature.login.ui.login.LoginViewModel
import com.composetest.feature.login.ui.login.WriteData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

//@ExtendWith(CoroutineExtensionContext::class)
@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private val buildConfigModelMock = BuildConfigFieldsModel(
        applicationId = "app",
        versionName = "1.0.0",
        versionCode = 0,
        buildType = "debug",
        flavor = "app"
    )
    private val buildConfigProvider: BuildConfigProvider = object : BuildConfigProvider {
        override val get: BuildConfigFieldsModel = buildConfigModelMock
    }
    private val authenticationUseCase: AuthenticationUseCase = mockk()

    private lateinit var viewModel: LoginViewModel

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)


    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        testScope.cancel()
    }

    @BeforeEach
    fun before() {
        viewModel = LoginViewModel(
            navigationProvider = mockk(relaxed = true),
            buildConfigProvider = buildConfigProvider,
            getCurrentAppThemeUseCase = mockk(),
            setCustomThemeUseCase = mockk(),
            authenticationUseCase = authenticationUseCase
        )
    }

    @Test
    fun `initial uiState`() = testScope.runTest {
        assertEquals(
            LoginUiState(
                versionName = buildConfigModelMock.versionNameForView,
                enableLoginButton = true
            ),
            viewModel.uiState.value
        )
    }

    @Test
    fun `misleanding credentials login`() = testScope.runTest {
        val observerState = observerStateFlow(viewModel.uiState)
        coEvery {
            authenticationUseCase.invoke(any(), any())
        } returns flow { throw InvalidCredentialsThrowable() }
        viewModel.executeCommand(WriteData("teste@teste.com", "password"))
        viewModel.executeCommand(Login)
        advanceUntilIdle()
        observerState.cancelJob()

        assertEquals(
            listOf(
                LoginUiState(
                    versionName = buildConfigModelMock.versionNameForView,
                    enableLoginButton = true
                ),
                LoginUiState(
                    versionName = buildConfigModelMock.versionNameForView,
                    enableLoginButton = true,
                    isLoading = true,
                ),
                LoginUiState(
                    versionName = buildConfigModelMock.versionNameForView,
                    enableLoginButton = true,
                    invalidCredentials = true,
                )
            ),
            observerState.collectedStates
        )
    }

    @Test
    fun `success login`() = testScope.runTest {
        val observerState = observerStateFlow(viewModel.uiState)
        coEvery {
            authenticationUseCase(any(), any())
        } returns flow { emit(true) }
        viewModel.executeCommand(WriteData("teste@teste.com", "password"))
        viewModel.executeCommand(Login)
        advanceUntilIdle()
        observerState.cancelJob()

        assertEquals(
            listOf(
                LoginUiState(
                    versionName = buildConfigModelMock.versionNameForView,
                    enableLoginButton = true
                ),
                LoginUiState(
                    versionName = buildConfigModelMock.versionNameForView,
                    enableLoginButton = true,
                    isLoading = true,
                ),
                LoginUiState(
                    versionName = buildConfigModelMock.versionNameForView,
                    enableLoginButton = true,
                )
            ),
            observerState.collectedStates
        )
    }

    @Test
    fun `error network`() = testScope.runTest {
        val observerState = observerStateFlow(viewModel.uiState)
        coEvery {
            authenticationUseCase.invoke(any(), any())
        } returns flow { throw RemoteNetworkThrowable() }

        viewModel.executeCommand(WriteData("teste@teste.com", "password"))
        viewModel.executeCommand(Login)
        advanceUntilIdle()
        observerState.cancelJob()

        assertEquals(
            listOf(
                LoginUiState(
                    versionName = buildConfigModelMock.versionNameForView,
                    enableLoginButton = true
                ),
                LoginUiState(
                    versionName = buildConfigModelMock.versionNameForView,
                    enableLoginButton = true,
                    isLoading = true,
                ),
                LoginUiState(
                    versionName = buildConfigModelMock.versionNameForView,
                    enableLoginButton = true,
                    errorAlertDialog = ErrorAlertDialog.NETWORK,
                )
            ),
            observerState.collectedStates
        )
    }
}