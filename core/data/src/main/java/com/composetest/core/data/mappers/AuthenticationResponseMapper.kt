package com.composetest.core.data.mappers

import com.composetest.common.extensions.orZero
import com.composetest.core.data.network.responses.AuthenticationResponse
import com.composetest.core.data.network.responses.UserResponse
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GetTokenResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthenticationResponseMapper @Inject constructor() {

    operator fun invoke(user: FirebaseUser?, token: GetTokenResult?) = AuthenticationResponse(
        token = token?.token.orEmpty(),
        authenticationDate = token?.authTimestamp.orZero,
        user = convertTo(user)
    )

    private fun convertTo(user: FirebaseUser?) = UserResponse(
        id = user?.uid.orEmpty(),
        name = user?.displayName,
        email = user?.email.orEmpty()
    )
}