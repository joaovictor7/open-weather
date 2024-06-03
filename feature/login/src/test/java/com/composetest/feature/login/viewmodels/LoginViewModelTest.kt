package com.composetest.feature.login.viewmodels

import com.composetest.core.utility.providers.BuildConfigProvider
import com.composetest.core.test.extensions.CoroutineExtension
import com.composetest.core.data.repositories.AuthenticationRepository
import com.composetest.core.utility.domain.models.BuildConfigFieldsModel
import com.composetest.feature.login.domain.usecases.AuthenticationUseCase
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

@ExtendWith(CoroutineExtension::class)
class LoginViewModelTest {

    private val buildConfigModelMock =
        BuildConfigFieldsModel(
            applicationId = "app",
            versionName = "1.0.0",
            versionCode = 0,
            buildType = "debug",
            flavor = "app"
        )

    private val buildConfigProvider: BuildConfigProvider = object : BuildConfigProvider {
        override val get: BuildConfigFieldsModel = buildConfigModelMock
    }
    private val authenticationRepository: AuthenticationRepository = mockk()
    private val authenticationUseCase = AuthenticationUseCase(authenticationRepository)

    private lateinit var viewModel: LoginViewModel

    @BeforeEach
    fun before() {
        viewModel = LoginViewModel(
            appThemeProvider = mockk(),
            navigationProvider = mockk(relaxed = true),
            buildConfigProvider = buildConfigProvider,
            authenticationUseCase = authenticationUseCase
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
            authenticationRepository.authentication(any())
        } returns flow { throw Exception() }
        viewModel.handleEvent(LoginEvent.WriteData("teste@teste.com", "password"))
        viewModel.handleEvent(LoginEvent.Login)
        assertEquals(
            LoginState(
                versionName = buildConfigModelMock.versionNameForView,
                invalidCredentials = true,
                enableLoginButton = true
            ),
            viewModel.state.value
        )
    }

    @Test
    fun `success login`() {
        coEvery {
            authenticationRepository.authentication(any())
        } returns flow { emit(true) }
        viewModel.handleEvent(LoginEvent.WriteData("teste@teste.com", "password"))
        viewModel.handleEvent(LoginEvent.Login)
        assertEquals(
            LoginState(
                versionName = buildConfigModelMock.versionNameForView,
                enableLoginButton = true,
                invalidCredentials = false
            ),
            viewModel.state.value
        )
    }
}