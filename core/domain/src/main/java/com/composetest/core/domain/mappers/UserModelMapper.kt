package com.composetest.core.domain.mappers

import com.composetest.core.data.network.responses.UserResponse
import com.composetest.core.database.domain.entities.UserEntity
import com.composetest.core.domain.models.UserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserModelMapper @Inject constructor() {

    operator fun invoke(response: UserResponse) = UserModel(
        id = response.id,
        email = response.email,
        name = response.name
    )

    operator fun invoke(entity: UserEntity?) = entity?.let {
        UserModel(
            id = it.id,
            email = it.email,
            name = it.name
        )
    }
}