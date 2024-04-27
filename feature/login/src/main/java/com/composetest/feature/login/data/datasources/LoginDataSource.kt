package com.composetest.feature.login.data.datasources

import com.composetest.feature.login.domain.models.LoginModel
import kotlinx.coroutines.flow.Flow

interface LoginDataSource {
    fun login(login: LoginModel): Flow<Boolean>
}