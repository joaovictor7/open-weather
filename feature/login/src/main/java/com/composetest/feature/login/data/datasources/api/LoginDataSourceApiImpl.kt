package com.composetest.feature.login.data.datasources.api

import com.composetest.feature.login.data.datasources.LoginDataSource
import com.composetest.feature.login.domain.models.LoginModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class LoginDataSourceApiImpl(private val firebaseAuth: FirebaseAuth): LoginDataSource {

    override fun login(login: LoginModel) = flow {
        firebaseAuth.signInWithEmailAndPassword(login.email, login.password).await()
        emit(true)
    }
}