package com.composetest.core.domain.usecases

import com.composetest.common.throwables.UnauthorizedRequestThrowable
import com.composetest.core.data.data.repositories.remote.AuthenticationRepository
import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.domain.mappers.SessionModelMapper
import com.composetest.core.domain.throwables.InvalidCredentialsThrowable
import com.composetest.core.domain.usecases.session.CreateSessionUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ViewModelScoped
class AuthenticationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val createSessionUseCase: CreateSessionUseCase,
    private val sessionModelMapper: SessionModelMapper
) {

    operator fun invoke(email: String, password: String) =
        authenticationRepository.authentication(
            AuthenticationRequest(email, password, true),
            sessionModelMapper::invoke
        ).catch {
            when (it) {
                is UnauthorizedRequestThrowable -> throw InvalidCredentialsThrowable()
                else -> throw it
            }
        }.onEach {
            createSessionUseCase(it)
        }.map { true }
}