package com.composetest.core.data.datasources.remote

import com.composetest.core.data.domain.requests.LoginRequest
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

internal class LoginDataSourceApiImpl(private val firebaseAuth: FirebaseAuth): LoginDataSource {
    override fun login(login: LoginRequest) = flow {
        firebaseAuth.signInWithEmailAndPassword(login.login, login.password).await()
        emit(true)
    }
}