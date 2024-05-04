package com.composetest.feature.login.login.data.datasources

import com.composetest.feature.login.login.domain.models.LoginModel
import kotlinx.coroutines.flow.Flow

internal interface LoginDataSource {
    fun login(login: LoginModel): Flow<Boolean>
}