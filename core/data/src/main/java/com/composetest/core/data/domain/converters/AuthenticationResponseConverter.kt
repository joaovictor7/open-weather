package com.composetest.core.data.domain.converters

import com.composetest.core.data.domain.models.network.responses.AuthenticationResponse
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthenticationResponseConverter @Inject constructor() {

    fun convertTo(response: FirebaseUser?) = AuthenticationResponse(
        id = response?.uid.orEmpty(),
        email = response?.email.orEmpty(),
        name = response?.displayName
    )
}