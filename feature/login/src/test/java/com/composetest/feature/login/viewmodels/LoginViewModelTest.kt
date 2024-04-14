package com.composetest.feature.login.viewmodels

import com.composetest.core.models.BuildConfigModel
import com.composetest.core.providers.BuildConfigProvider
import com.composetest.core.test.shared.CourotineExtension
import com.composetest.feature.login.infra.datasource.LoginDataSource
import com.composetest.feature.login.infra.repositories.LoginRepository
import com.composetest.feature.login.infra.usecases.LoginUseCase
import com.composetest.feature.login.models.LoginModel
import com.composetest.feature.login.ui.LoginAction
import com.composetest.feature.login.ui.LoginState
import com.composetest.feature.login.ui.LoginViewModel
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
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
    private val loginDataSource: LoginDataSource = mock()
    private val loginRepository: LoginRepository = LoginRepository(loginDataSource)
    private val loginUseCase = LoginUseCase(loginRepository)

    private lateinit var viewModel: LoginViewModel

    @BeforeEach
    fun before() {
        viewModel = LoginViewModel(
            homeDestination = mock(),
            navigationProvider = mock(),
            buildConfigProvider = buildConfigProvider,
            loginUseCase = loginUseCase
        )
    }

    @Test
    fun `initial state`() {
        assertEquals(
            LoginState(versionName = buildConfigModelMock.versionNameWithVersionCode),
            viewModel.state.value
        )
    }

    @Test
    fun `misleanding login`() {
        `when`(
            loginDataSource.login(LoginModel("teste@teste.com", "password"))
        ).thenReturn(
            flow { throw Exception() }
        )
        viewModel.handleAction(LoginAction.WriteData("teste@teste.com", "password"))
        viewModel.handleAction(LoginAction.ClickEnter)
        assertEquals(
            LoginState(
                versionName = buildConfigModelMock.versionNameWithVersionCode,
                loginError = true
            ),
            viewModel.state.value
        )
    }

    @Test
    fun `success login`() {
        `when`(
            loginDataSource.login(LoginModel("teste@teste.com", "password"))
        ).thenReturn(
            flow { emit(true) }
        )
        viewModel.handleAction(LoginAction.WriteData("teste@teste.com", "password"))
        viewModel.handleAction(LoginAction.ClickEnter)
        assertEquals(
            LoginState(
                versionName = buildConfigModelMock.versionNameWithVersionCode,
                loginError = false
            ),
            viewModel.state.value
        )
    }
}