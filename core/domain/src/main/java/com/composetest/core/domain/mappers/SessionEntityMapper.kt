package com.composetest.core.domain.mappers

import com.composetest.core.database.domain.entities.SessionEntity
import com.composetest.core.domain.models.SessionWithUserModel
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
}