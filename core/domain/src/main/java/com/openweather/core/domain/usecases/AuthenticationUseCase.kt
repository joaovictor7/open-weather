package com.openweather.core.domain.usecases

import com.openweather.common.throwables.UnauthorizedRequestThrowable
import com.openweather.core.data.data.repositories.remote.AuthenticationRepository
import com.openweather.core.data.network.requests.AuthenticationRequest
import com.openweather.core.domain.managers.SessionManager
import com.openweather.core.domain.mappers.SessionModelMapper
import com.openweather.core.domain.throwables.InvalidCredentialsThrowable
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val sessionManager: SessionManager,
    private val sessionModelMapper: SessionModelMapper
) {

    suspend operator fun invoke(email: String, password: String) {
        val response = runCatching {
            authenticationRepository.authentication(
                AuthenticationRequest(email, password),
                sessionModelMapper::invoke
            )
        }.getOrElse {
            when (it) {
                is UnauthorizedRequestThrowable -> throw InvalidCredentialsThrowable()
                else -> throw it
            }
        }
        sessionManager.createSession(response)
    }
}