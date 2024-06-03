package com.composetest.core.domain.converters

import com.composetest.core.database.domain.entities.UserEntity
import com.composetest.core.domain.models.UserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserEntityConverter @Inject constructor() {

    fun convertTo(model: UserModel) = UserEntity(
        id = model.id,
        name = model.name,
        email = model.email
    )
}