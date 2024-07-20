package com.composetest.feature.login.viewmodels

import com.composetest.common.enums.BuildType
import com.composetest.common.enums.Flavor
import com.composetest.common.models.BuildConfigModel
import com.composetest.common.providers.BuildConfigProvider
import com.composetest.common.throwables.NetworkThrowable
import com.composetest.core.designsystem.components.alertdialogs.params.ErrorAlertDialogParam
import com.composetest.core.domain.throwables.InvalidCredentialsThrowable
import com.composetest.core.domain.usecases.AuthenticationUseCase
import com.composetest.core.domain.usecases.session.GetNeedsLoginBySessionUseCase
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.router.destinations.home.navtypes.InnerHome
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.providers.NavigationProvider
import com.composetest.core.test.interfaces.CoroutinesTest
import com.composetest.core.test.utils.runStateFlowTest
import com.composetest.feature.login.models.LoginFormModel
import com.composetest.feature.login.ui.login.Login
import com.composetest.feature.login.ui.login.LoginUiState
import com.composetest.feature.login.ui.login.LoginViewModel
import com.composetest.feature.login.ui.login.WriteData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LoginViewModelTest : CoroutinesTest {

    override lateinit var testDispatcher: TestDispatcher

    private val buildConfigModelMock = BuildConfigModel(
        applicationId = "app",
        versionName = "1.0.0",
        versionCode = 0,
        buildType = BuildType.DEBUG,
        flavor = Flavor.FULL,
        androidSdkVersion = 34
    )
    private val navigationProvider: NavigationProvider = mockk(relaxed = true)
    private val buildConfigProvider: BuildConfigProvider = object : BuildConfigProvider {
        override val get: BuildConfigModel = buildConfigModelMock
    }
    private val authenticationUseCase: AuthenticationUseCase = mockk()
    private val getNeedsLoginBySessionUseCase: GetNeedsLoginBySessionUseCase = mockk {
        coEvery { this@mockk.invoke() } returns true
    }

    private lateinit var viewModel: LoginViewModel

    @BeforeEach
    fun before() {
        viewModel = initViewModel()
    }

    @Test
    fun `initial uiState`() =
        runStateFlowTest(testDispatcher, viewModel.uiState) { job, collectedStates ->
            coEvery { getNeedsLoginBySessionUseCase() } answers { false }
            job.cancel()

            assertEquals(
                listOf(
                    LoginUiState(
                        needsLogin = true,
                        versionName = buildConfigModelMock.versionNameForView,
                        enableLoginButton = true
                    )
                ),
                collectedStates
            )
        }

    @Test
    fun `initial uiState when not need login`() {
        coEvery { getNeedsLoginBySessionUseCase() } returns false
        val viewModel = initViewModel()
        runStateFlowTest(testDispatcher, viewModel.uiState) { job, collectedStates ->
            job.cancel()

            assertEquals(
                listOf(LoginUiState()),
                collectedStates
            )
            coVerify {
                navigationProvider.asyncNavigate(
                    HomeDestination("teste", InnerHome("te", "23232")),
                    NavigationMode.REMOVE_ALL_SCREENS_STACK
                )
            }
        }
    }

    @Test
    fun `misleanding credentials login`() =
        runStateFlowTest(testDispatcher, viewModel.uiState) { job, collectedStates ->
            coEvery {
                authenticationUseCase.invoke(any(), any())
            } coAnswers { withContext(testDispatcher) { throw InvalidCredentialsThrowable() } }

            viewModel.executeCommand(WriteData("teste@teste.com", "password"))
            viewModel.executeCommand(Login)
            job.cancel()

            assertEquals(
                listOf(
                    LoginUiState(
                        needsLogin = true,
                        versionName = buildConfigModelMock.versionNameForView,
                        enableLoginButton = true
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = buildConfigModelMock.versionNameForView,
                        enableLoginButton = true,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = buildConfigModelMock.versionNameForView,
                        enableLoginButton = true,
                        isLoading = true,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = buildConfigModelMock.versionNameForView,
                        enableLoginButton = true,
                        invalidCredentials = true,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    )
                ),
                collectedStates
            )
        }

    @Test
    fun `success login`() =
        runStateFlowTest(testDispatcher, viewModel.uiState) { job, collectedStates ->
            coEvery {
                withContext(testDispatcher) { authenticationUseCase(any(), any()) }
            } coAnswers { withContext(testDispatcher) {} }

            viewModel.executeCommand(WriteData("teste@teste.com", "password"))
            viewModel.executeCommand(Login)
            job.cancel()

            assertEquals(
                listOf(
                    LoginUiState(
                        needsLogin = true,
                        versionName = buildConfigModelMock.versionNameForView,
                        enableLoginButton = true
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = buildConfigModelMock.versionNameForView,
                        enableLoginButton = true,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = buildConfigModelMock.versionNameForView,
                        enableLoginButton = true,
                        isLoading = true,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = buildConfigModelMock.versionNameForView,
                        enableLoginButton = true,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    )
                ),
                collectedStates
            )
            coVerify {
                navigationProvider.asyncNavigate(
                    HomeDestination("teste", InnerHome("te", "23232")),
                    NavigationMode.REMOVE_ALL_SCREENS_STACK
                )
            }
        }

    @Test
    fun `error network`() =
        runStateFlowTest(testDispatcher, viewModel.uiState) { job, collectedStates ->
            coEvery {
                authenticationUseCase.invoke(any(), any())
            } coAnswers { withContext(testDispatcher) { throw NetworkThrowable() } }

            viewModel.executeCommand(WriteData("teste@teste.com", "password"))
            viewModel.executeCommand(Login)
            job.cancel()

            assertEquals(
                listOf(
                    LoginUiState(
                        needsLogin = true,
                        versionName = buildConfigModelMock.versionNameForView,
                        enableLoginButton = true
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = buildConfigModelMock.versionNameForView,
                        enableLoginButton = true,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = buildConfigModelMock.versionNameForView,
                        enableLoginButton = true,
                        isLoading = true,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    ),
                    LoginUiState(
                        needsLogin = true,
                        versionName = buildConfigModelMock.versionNameForView,
                        enableLoginButton = true,
                        errorAlertDialogParam = ErrorAlertDialogParam.networkErrorAlertDialogParam,
                        loginFormModel = LoginFormModel(
                            email = "teste@teste.com",
                            password = "password"
                        )
                    )
                ),
                collectedStates
            )
        }

    private fun initViewModel() = LoginViewModel(
        navigationProvider = navigationProvider,
        buildConfigProvider = buildConfigProvider,
        setAppThemeUseCase = mockk(),
        authenticationUseCase = authenticationUseCase,
        analyticsUseCase = mockk(relaxed = true),
        getNeedsLoginBySessionUseCase = getNeedsLoginBySessionUseCase
    )
}