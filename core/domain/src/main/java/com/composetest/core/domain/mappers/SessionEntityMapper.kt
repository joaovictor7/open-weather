package com.composetest.core.domain.mappers

import com.composetest.core.database.entities.SessionEntity
import com.composetest.core.database.entities.partialupdate.EndDateSessionEntityUpdate
import com.composetest.core.domain.models.session.SessionModel
import com.composetest.core.domain.models.session.SessionWithUserModel
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionEntityMapper @Inject constructor() {

    operator fun invoke(model: SessionWithUserModel) = SessionEntity(
        token = model.token,
        initialDate = model.initialDate,
        endDate = model.endDate,
        userId = model.user.id
    )

    operator fun invoke(id: Long, endDate: LocalDateTime) = EndDateSessionEntityUpdate(
        sessionId = id,
        endDate = endDate,
    )
}