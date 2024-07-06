package com.composetest.api.plugins

import com.composetest.api.firebase.UserModel
import com.composetest.api.firebase.firebase
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication

fun Application.configureFirebaseAuth() {
    install(Authentication) {
        firebase {
            validate {
                UserModel(
                    userId = it.uid,
                    name = it.name,
                    email = it.email
                )
            }
        }
    }
}