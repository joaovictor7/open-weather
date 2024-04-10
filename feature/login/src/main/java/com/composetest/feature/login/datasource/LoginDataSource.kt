package com.composetest.feature.login.datasource

import com.composetest.feature.login.models.LoginModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.flow

class LoginDataSource(private val firebaseAuth: FirebaseAuth) {

    fun login(login: LoginModel)  {

    }

}