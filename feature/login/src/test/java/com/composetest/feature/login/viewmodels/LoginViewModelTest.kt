package com.composetest.feature.login.viewmodels

import com.composetest.common.BuildConfigProvider
import com.composetest.core.test.extensions.CoroutineExtension
import com.composetest.core.data.repositories.AuthenticationRepository
import com.composetest.core.domain.usecases.AuthenticationUseCase
import com.composetest.feature.login.ui.login.LoginUiState
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
        com.composetest.common.models.BuildConfigFieldsModel(
            applicationId = "app",
            versionName = "1.0.0",
            versionCode = 0,
            buildType = "debug",
            flavor = "app"
        )

    private val buildConfigProvider: com.composetest.common.BuildConfigProvider = object :
        com.composetest.common.BuildConfigProvider {
        override val get: com.composetest.common.models.BuildConfigFieldsModel = buildConfigModelMock
    }
    private val authenticationRepository: AuthenticationRepository = mockk()
    private val authenticationUseCase = AuthenticationUseCase(authenticationRepository)

    private lateinit var viewModel: LoginViewModel

    @BeforeEach
    fun before() {
        viewModel = LoginViewModel(
            setCustomThemeUseCase = mockk(),
            navigationProvider = mockk(relaxed = true),
            buildConfigProvider = buildConfigProvider,
            authenticationUseCase = authenticationUseCase
        )
    }

    @Test
    fun `initial uiState`() {
        assertEquals(
            LoginUiState(
                versionName = buildConfigModelMock.versionNameForView,
                enableLoginButton = true
            ),
            viewModel.uiState.value
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
            LoginUiState(
                versionName = buildConfigModelMock.versionNameForView,
                invalidCredentials = true,
                enableLoginButton = true
            ),
            viewModel.uiState.value
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
            LoginUiState(
                versionName = buildConfigModelMock.versionNameForView,
                enableLoginButton = true,
                invalidCredentials = false
            ),
            viewModel.uiState.value
        )
    }
}