package com.composetest.core.domain.mappers

import com.composetest.core.database.entities.UserEntity
import com.composetest.core.domain.models.UserModel
import javax.inject.Inject

class UserEntityMapper @Inject constructor() {

    operator fun invoke(model: UserModel) = UserEntity(
        id = model.id,
        name = model.name,
        email = model.email
    )
}