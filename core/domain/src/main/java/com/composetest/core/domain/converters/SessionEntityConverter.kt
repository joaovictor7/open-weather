package com.composetest.core.domain.converters

import com.composetest.core.domain.models.SessionModel
import com.composetest.core.database.domain.entities.SessionEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionEntityConverter @Inject constructor() {

    fun convertTo(model: SessionModel) = SessionEntity(
        initialDate = model.initialDate,
        userId = model.user.id
    )
}