package com.composetest.core.domain.converters

import com.composetest.core.data.domain.models.network.responses.AuthenticationResponse
import com.composetest.core.database.domain.entities.UserEntity
import com.composetest.core.domain.models.UserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserModelConverter @Inject constructor() {

    fun convertTo(response: AuthenticationResponse) = UserModel(
        id = response.id,
        email = response.email,
        name = response.name
    )

    fun convertTo(entity: UserEntity) = UserModel(
        id = entity.id,
        email = entity.email,
        name = entity.name
    )
}