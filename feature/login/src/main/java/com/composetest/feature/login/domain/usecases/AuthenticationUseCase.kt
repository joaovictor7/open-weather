package com.composetest.feature.login.domain.usecases

import com.composetest.core.data.domain.models.network.requests.AuthenticationRequest
import com.composetest.core.data.repositories.AuthenticationRepository
import com.composetest.core.domain.converters.SessionModelConverter
import com.composetest.core.domain.usecases.SessionUseCase
import com.composetest.feature.login.domain.models.LoginFormModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

@ViewModelScoped
internal class AuthenticationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val sessionUseCase: SessionUseCase,
    private val converter: SessionModelConverter
) {

    fun authentication(
        authentication: LoginFormModel
    ) = authenticationRepository.authentication(
        AuthenticationRequest(authentication.email, authentication.password),
        converter::invoke
    ).onEach(sessionUseCase::createSession).transform { emit(true) }
}