package com.openweather.core.domain.mappers

import com.openweather.core.data.network.responses.UserResponse
import com.openweather.core.domain.models.UserModel
import javax.inject.Inject

class UserModelMapper @Inject constructor() {

    operator fun invoke(response: UserResponse) = UserModel(
        id = response.id,
        email = response.email,
        name = response.name
    )
}