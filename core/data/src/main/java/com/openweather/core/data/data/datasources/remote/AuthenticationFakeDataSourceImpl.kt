package com.openweather.core.data.data.datasources.remote

import com.openweather.common.providers.DateTimeProvider
import com.openweather.core.data.managers.RemoteCallManager
import com.openweather.core.data.network.requests.AuthenticationRequest
import com.openweather.core.data.network.responses.AuthenticationResponse
import com.openweather.core.data.network.responses.SessionResponse
import com.openweather.core.data.network.responses.UserResponse

internal class AuthenticationFakeDataSourceImpl(
    private val dateTimeProvider: DateTimeProvider,
    private val safeRemoteCallManager: RemoteCallManager
) : AuthenticationDataSource {

    override suspend fun authentication(authenticationRequest: AuthenticationRequest) =
        safeRemoteCallManager.safeRemoteCall {
            val sessionStartDate = dateTimeProvider.nowDateTime
            val sessionEndDate = sessionStartDate.plusMinutes(PLUS_FAKE_SESSION_DURATION)
            AuthenticationResponse(
                user = UserResponse(
                    id = "123",
                    name = "Teste",
                    email = "teste@teste.com"
                ),
                sessionResponse = SessionResponse(
                    token = "43reddcdsfe434323cdf3434",
                    startDate = sessionStartDate.toString(),
                    endDate = sessionEndDate.toString(),
                )
            )
        }

    private companion object {
        const val PLUS_FAKE_SESSION_DURATION = 5L
    }
}