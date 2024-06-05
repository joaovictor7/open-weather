package com.composetest.core.data.domain.converters

import com.composetest.core.data.domain.models.network.responses.AuthenticationResponse
import com.composetest.core.data.domain.models.network.responses.UserResponse
import com.composetest.core.utility.extensions.orZero
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GetTokenResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthenticationResponseConverter @Inject constructor() {

    fun convertTo(user: FirebaseUser?, token: GetTokenResult?) = AuthenticationResponse(
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