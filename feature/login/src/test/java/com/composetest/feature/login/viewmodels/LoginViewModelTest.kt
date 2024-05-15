package com.composetest.feature.login.viewmodels

import com.composetest.core.domain.models.BuildConfigModel
import com.composetest.core.providers.BuildConfigProvider
import com.composetest.core.test.shared.CourotineExtension
import com.composetest.core.ui.providers.AppThemeProvider
import com.composetest.feature.login.data.datasources.LoginDataSource
import com.composetest.feature.login.data.repositories.LoginRepository
import com.composetest.feature.login.domain.usecases.LoginUseCase
import com.composetest.feature.login.ui.login.LoginEvent
import com.composetest.feature.login.ui.login.LoginState
import com.composetest.feature.login.ui.login.LoginViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.lang.Exception

@ExtendWith(CourotineExtension::class)
class LoginViewModelTest {

    private val buildConfigModelMock = BuildConfigModel(
        applicationId = "app",
        versionName = "1.0.0",
        versionCode = 0,
        buildType = "debug",
        flavor = "app",
        dynamicColors = false,
        useMock = true
    )

    private val buildConfigProvider: BuildConfigProvider = object : BuildConfigProvider {
        override val buildConfigModel: BuildConfigModel = buildConfigModelMock
    }
    private val loginDataSource: LoginDataSource = mockk()
    private val loginRepository: LoginRepository = LoginRepository(loginDataSource)
    private val loginUseCase = LoginUseCase(loginRepository)

    private lateinit var viewModel: LoginViewModel

    @BeforeEach
    fun before() {
        viewModel = LoginViewModel(
            appThemeProvider = AppThemeProvider(),
            navigationProvider = mockk(relaxed = true),
            buildConfigProvider = buildConfigProvider,
            loginUseCase = loginUseCase
        )
    }

    @Test
    fun `initial state`() {
        assertEquals(
            LoginState(
                versionName = buildConfigModelMock.versionNameForView,
                enableLoginButton = true
            ),
            viewModel.state.value
        )
    }

    @Test
    fun `misleanding login`() {
        coEvery {
            loginDataSource.login(any())
        } returns (flow { throw Exception() })
        viewModel.handleEvent(LoginEvent.WriteData("teste@teste.com", "password"))
        viewModel.handleEvent(LoginEvent.Login)
        assertEquals(
            LoginState(
                versionName = buildConfigModelMock.versionNameForView,
                loginError = true,
                enableLoginButton = true
            ),
            viewModel.state.value
        )
    }

    @Test
    fun `success login`() {
        coEvery {
            loginDataSource.login(any())
        } returns flow { emit(true) }
        viewModel.handleEvent(LoginEvent.WriteData("teste@teste.com", "password"))
        viewModel.handleEvent(LoginEvent.Login)
        assertEquals(
            LoginState(
                versionName = buildConfigModelMock.versionNameForView,
                enableLoginButton = true,
                loginError = false
            ),
            viewModel.state.value
        )
    }
}