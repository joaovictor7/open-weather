package com.composetest.core.domain.mappers

import com.composetest.common.extensions.secondsToLocalDateTime
import com.composetest.core.data.network.responses.AuthenticationResponse
import com.composetest.core.domain.models.SessionWithUserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionModelMapper @Inject constructor(
    private val userModelMapper: UserModelMapper
) {

    operator fun invoke(response: AuthenticationResponse) = SessionWithUserModel(
        token = response.token,
        initialDate = response.authenticationDate.secondsToLocalDateTime,
        user = userModelMapper(response.user)
    )
}