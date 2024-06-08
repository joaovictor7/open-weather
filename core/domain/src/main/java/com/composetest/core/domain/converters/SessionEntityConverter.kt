package com.composetest.core.domain.converters

import com.composetest.core.database.domain.entities.SessionEntity
import com.composetest.core.domain.models.SessionWithUserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionEntityConverter @Inject constructor() {

    operator fun invoke(model: SessionWithUserModel) = SessionEntity(
        token = model.token,
        initialDate = model.initialDate,
        endDate = model.endDate,
        userId = model.user.id
    )
}