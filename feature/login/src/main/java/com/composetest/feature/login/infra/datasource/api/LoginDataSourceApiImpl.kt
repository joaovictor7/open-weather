package com.composetest.feature.login.infra.datasource.api

import com.composetest.feature.login.infra.datasource.LoginDataSource
import com.composetest.feature.login.models.LoginModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class LoginDataSourceApiImpl(private val firebaseAuth: FirebaseAuth): LoginDataSource {

    override fun login(login: LoginModel) = flow {
        firebaseAuth.signInWithEmailAndPassword(login.email, login.password).await()
        emit(true)
    }
}