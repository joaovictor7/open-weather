package com.composetest.core.domain.converters

import com.composetest.core.data.domain.models.network.responses.AuthenticationResponse
import com.composetest.core.domain.models.SessionWithUserModel
import com.composetest.core.utility.extensions.secondsToLocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionModelConverter @Inject constructor(
    private val userModelConverter: UserModelConverter
) {

    operator fun invoke(response: AuthenticationResponse) = SessionWithUserModel(
        token = response.token,
        initialDate = response.authenticationDate.secondsToLocalDateTime,
        user = userModelConverter(response.user)
    )
}