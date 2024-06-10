package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.remote.FirebaseAuthDataSource
import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
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
    ) = dataSource.authentication(request).map { converter.invoke(it) }
}