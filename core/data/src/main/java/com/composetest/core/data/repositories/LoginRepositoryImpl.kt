package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.remote.FirebaseAuthDataSource
import com.composetest.core.data.domain.models.requests.LoginRequest
import com.composetest.core.data.domain.throwable.InvalidCredentialsThrowable
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LoginRepositoryImpl @Inject constructor(
    private val firebaseAuthDataSource: FirebaseAuthDataSource
) : LoginRepository {

    override fun login(login: LoginRequest) =
        firebaseAuthDataSource.login(login).catch {
            when (it) {
                is FirebaseAuthInvalidCredentialsException -> throw InvalidCredentialsThrowable()
                else -> throw it
            }
        }.transform {
            emit(true)
        }
}