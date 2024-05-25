package com.composetest.feature.login.domain.usecases

import com.composetest.core.data.domain.remote.requests.LoginRequest
import com.composetest.core.data.repositories.LoginRepository
import com.composetest.feature.login.domain.models.LoginModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    fun login(loginModel: LoginModel) = loginRepository.login(
        LoginRequest(loginModel.email, loginModel.password)
    )
}