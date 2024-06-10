package com.composetest.core.domain.converters

import com.composetest.core.data.network.responses.UserResponse
import com.composetest.core.database.domain.entities.UserEntity
import com.composetest.core.domain.models.UserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserModelConverter @Inject constructor() {

    operator fun invoke(response: UserResponse) = UserModel(
        id = response.id,
        email = response.email,
        name = response.name
    )

    operator fun invoke(entity: UserEntity) = UserModel(
        id = entity.id,
        email = entity.email,
        name = entity.name
    )
}