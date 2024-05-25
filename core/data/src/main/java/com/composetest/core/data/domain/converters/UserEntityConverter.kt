package com.composetest.core.data.domain.converters

import com.composetest.core.data.domain.remote.responses.UserResponse
import com.composetest.core.database.entities.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserEntityConverter @Inject constructor() {

    fun convertTo(userModel: UserResponse) = UserEntity(
        id = userModel.id,
        email = userModel.email
    )
}