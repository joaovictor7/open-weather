package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.remote.FirebaseAuthDataSource
import com.composetest.core.data.domain.models.network.requests.AuthenticationRequest
import com.composetest.core.data.domain.models.network.responses.AuthenticationResponse
import com.composetest.core.data.throwables.InvalidCredentialsThrowable
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthenticationRepositoryImpl @Inject constructor(
    private val dataSource: FirebaseAuthDataSource
) : AuthenticationRepository {

    override fun <T> authentication(
        request: AuthenticationRequest,
        converter: (AuthenticationResponse) -> T
    ) = dataSource.authentication(request)
        .catch {
            when (it) {
                is FirebaseAuthInvalidCredentialsException -> throw InvalidCredentialsThrowable()
                else -> throw it
            }
        }.map { converter.invoke(it) }
}