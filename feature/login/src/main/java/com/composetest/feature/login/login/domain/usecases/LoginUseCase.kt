package com.composetest.feature.login.login.domain.usecases

import com.composetest.feature.login.login.data.repositories.LoginRepository
import com.composetest.feature.login.login.domain.models.LoginModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    fun login(loginModel: LoginModel) = loginRepository.login(loginModel)
}