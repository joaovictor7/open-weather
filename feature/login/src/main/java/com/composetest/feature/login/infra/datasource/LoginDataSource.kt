package com.composetest.feature.login.infra.datasource

import com.composetest.feature.login.models.LoginModel
import kotlinx.coroutines.flow.Flow

interface LoginDataSource {
    fun login(login: LoginModel): Flow<Boolean>
}