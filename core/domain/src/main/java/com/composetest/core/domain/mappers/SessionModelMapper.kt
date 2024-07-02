package com.composetest.core.domain.mappers

import com.composetest.common.extensions.secondsToLocalDateTime
import com.composetest.core.data.network.responses.AuthenticationResponse
import com.composetest.core.database.entities.SessionEntity
import com.composetest.core.domain.models.session.SessionModel
import com.composetest.core.domain.models.session.SessionWithUserModel
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

    operator fun invoke(entity: SessionEntity?) = entity?.let {
        SessionModel(
            id = it.id,
            token = it.token,
            initialDate = it.initialDate,
            endDate = it.endDate
        )
    }
}