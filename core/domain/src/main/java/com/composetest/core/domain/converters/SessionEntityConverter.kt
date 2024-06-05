package com.composetest.core.domain.converters

import com.composetest.core.domain.models.SessionModel
import com.composetest.core.database.domain.entities.SessionEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionEntityConverter @Inject constructor() {

    fun convertTo(model: SessionModel) = SessionEntity(
        token = model.token,
        initialDate = model.initialDate,
        endDate = model.endDate,
        userId = model.user.id
    )
}