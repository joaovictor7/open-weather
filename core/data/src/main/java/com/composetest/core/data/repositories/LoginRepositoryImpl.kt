package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.DatabaseDataSource
import com.composetest.core.data.datasources.remote.FirebaseAuthDataSource
import com.composetest.core.data.domain.converters.UserEntityConverter
import com.composetest.core.data.domain.remote.requests.LoginRequest
import com.composetest.core.data.domain.remote.responses.UserResponse
import com.composetest.core.data.domain.throwables.InvalidCredentialsThrowable
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LoginRepositoryImpl @Inject constructor(
    private val firebaseAuthDataSource: FirebaseAuthDataSource,
    private val databaseDataSource: DatabaseDataSource,
    private val converter: UserEntityConverter
) : LoginRepository {

    override fun login(login: LoginRequest) = firebaseAuthDataSource.login(login)
        .catch {
            when (it) {
                is FirebaseAuthInvalidCredentialsException -> throw InvalidCredentialsThrowable()
                else -> throw it
            }
        }
        .onEach { insertUser(it) }
        .transform { emit(true) }

    private fun insertUser(response: UserResponse) {
        databaseDataSource.appDatabase.userDao().insert(converter.convertTo(response))
    }
}