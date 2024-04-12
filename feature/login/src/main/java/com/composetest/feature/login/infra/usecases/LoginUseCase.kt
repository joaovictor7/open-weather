package com.composetest.feature.login.infra.usecases

import com.composetest.feature.login.infra.repositories.LoginRepository
import com.composetest.feature.login.models.LoginModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    fun login(loginModel: LoginModel) = loginRepository.login(loginModel)
}