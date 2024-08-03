package com.openweather.core.domain.mappers

import com.openweather.core.database.entities.SessionEntity
import com.openweather.core.domain.models.session.SessionWithUserModel
import javax.inject.Inject

class SessionEntityMapper @Inject constructor() {

    operator fun invoke(model: SessionWithUserModel) = SessionEntity(
        token = model.token,
        startDate = model.startDate,
        endDate = model.endDate,
        userId = model.user.id
    )
}