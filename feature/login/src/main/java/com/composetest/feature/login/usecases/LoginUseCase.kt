package com.composetest.feature.login.usecases

import com.composetest.feature.login.infra.repositories.LoginRepository
import com.composetest.feature.login.models.LoginModel

class LoginUseCase(private val loginRepository: LoginRepository) {
    fun login(loginModel: LoginModel) = loginRepository.login(loginModel)
}