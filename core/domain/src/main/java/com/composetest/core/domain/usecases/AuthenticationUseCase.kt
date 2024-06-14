package com.composetest.core.domain.usecases

import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.repositories.remote.AuthenticationRepository
import com.composetest.core.domain.mappers.SessionModelMapper
import com.composetest.common.throwables.InvalidCredentialsThrowable
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

@ViewModelScoped
class AuthenticationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val createSessionUseCase: CreateSessionUseCase,
    private val sessionModelMapper: SessionModelMapper
) {

    operator fun invoke(email: String, password: String) = authenticationRepository.authentication(
        AuthenticationRequest(email, password),
        sessionModelMapper::invoke
    ).catch {
        when (it) {
            is FirebaseAuthInvalidCredentialsException -> throw InvalidCredentialsThrowable()
            else -> throw it
        }
    }.onEach(createSessionUseCase::invoke).transform { emit(true) }
}