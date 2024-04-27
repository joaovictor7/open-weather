package com.composetest.feature.login.data.usecases

import com.composetest.feature.login.data.repositories.LoginRepository
import com.composetest.feature.login.domain.models.LoginModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    fun login(loginModel: LoginModel) = loginRepository.login(loginModel)
}