package com.composetest.core.domain.mappers

import com.composetest.core.database.entities.SessionEntity
import com.composetest.core.domain.models.session.SessionWithUserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionEntityMapper @Inject constructor() {

    operator fun invoke(model: SessionWithUserModel) = SessionEntity(
        token = model.token,
        startDate = model.startDate,
        endDate = model.endDate,
        userId = model.user.id
    )
}