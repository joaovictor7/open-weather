package com.composetest.core.domain.converters

import com.composetest.core.data.domain.models.network.responses.AuthenticationResponse
import com.composetest.core.domain.models.SessionModel
import com.composetest.core.utility.extensions.secondsToLocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionModelConverter @Inject constructor(
    private val userModelConverter: UserModelConverter
) {

    fun convertTo(response: AuthenticationResponse) = SessionModel(
        token = response.token,
        initialDate = response.authenticationDate.secondsToLocalDateTime,
        user = userModelConverter.convertTo(response.user)
    )
}