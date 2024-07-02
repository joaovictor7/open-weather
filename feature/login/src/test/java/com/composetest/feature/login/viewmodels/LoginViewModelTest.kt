package com.composetest.feature.login.viewmodels

import com.composetest.common.models.BuildConfigFieldsModel
import com.composetest.common.providers.BuildConfigProvider
import com.composetest.common.throwables.InvalidCredentialsThrowable
import com.composetest.common.throwables.RemoteNetworkThrowable
import com.composetest.core.designsystem.components.alertdialogs.enums.ErrorAlertDialog
import com.composetest.core.domain.usecases.AuthenticationUseCase
import com.composetest.core.test.utils.runStateFlowTest
import com.composetest.core.test.interfaces.CoroutineTest
import com.composetest.feature.login.ui.login.Login
import com.composetest.feature.login.ui.login.LoginUiState
import com.composetest.feature.login.ui.login.LoginViewModel
import com.composetest.feature.login.ui.login.WriteData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestDispatcher
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LoginViewModelTest : CoroutineTest {

    override lateinit var testDispatcher: TestDispatcher

    private val buildConfigModelMock = BuildConfigFieldsModel(
        applicationId = "app",
        versionName = "1.0.0",
        versionCode = 0,
        buildType = "debug",
        flavor = "app",
        androidSdkVersion = 34
    )
    private val buildConfigProvider: BuildConfigProvider = object : BuildConfigProvider {
        override val get: BuildConfigFieldsModel = buildConfigModelMock
    }
    private val authenticationUseCase: AuthenticationUseCase = mockk()

    private lateinit var viewModel: LoginViewModel

    @BeforeEach
    fun before() {
        viewModel = LoginViewModel(
            navigationProvider = mockk(relaxed = true),
            buildConfigProvider = buildConfigProvider,
            getAppThemeStateUseCase = mockk(),
            setAppThemeUseCase = mockk(),
            authenticationUseCase = authenticationUseCase,
            analyticsUseCase = mockk(relaxed = true),
            dispatcher = testDispatcher
        )
    }

    @Test
    fun `initial uiState`() =
        runStateFlowTest(testDispatcher, viewModel.uiState) { job, collectedStates ->
            job.cancel()

            assertEquals(
                listOf(
                    LoginUiState(
                        versionName = buildConfigModelMock.versionNameForView,
                        enableLoginButton = true
                    )
                ),
                collectedStates
            )
        }

    @Test
    fun `misleanding credentials login`() =
        runStateFlowTest(testDispatcher, viewModel.uiState) { job, collectedStates ->
            coEvery {
                authenticationUseCase.invoke(any(), any())
            } returns flow { throw InvalidCredentialsThrowable() }

            viewModel.executeCommand(WriteData("teste@teste.com", "password"))
            viewModel.executeCommand(Login)
            job.cancel()

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
                collectedStates
            )
        }

    @Test
    fun `success login`() =
        runStateFlowTest(testDispatcher, viewModel.uiState) { job, collectedStates ->
            coEvery {
                authenticationUseCase(any(), any())
            } returns flow { emit(true) }

            viewModel.executeCommand(WriteData("teste@teste.com", "password"))
            viewModel.executeCommand(Login)
            job.cancel()

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
                collectedStates
            )
        }

    @Test
    fun `error network`() =
        runStateFlowTest(testDispatcher, viewModel.uiState) { job, collectedStates ->
            coEvery {
                authenticationUseCase.invoke(any(), any())
            } returns flow { throw RemoteNetworkThrowable() }

            viewModel.executeCommand(WriteData("teste@teste.com", "password"))
            viewModel.executeCommand(Login)
            job.cancel()

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
                collectedStates
            )
        }
}