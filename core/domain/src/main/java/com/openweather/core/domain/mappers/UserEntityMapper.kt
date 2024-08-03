package com.openweather.core.domain.mappers

import com.openweather.core.database.entities.UserEntity
import com.openweather.core.domain.models.UserModel
import javax.inject.Inject

class UserEntityMapper @Inject constructor() {

    operator fun invoke(model: UserModel) = UserEntity(
        id = model.id,
        name = model.name,
        email = model.email
    )
}