package com.composetest.api.routes

import com.composetest.api.firebase.FIREBASE_AUTH
import com.composetest.api.firebase.UserModel
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.put

fun Route.configureAuthenticationRouting() {
    authenticate(FIREBASE_AUTH) {
        get("/authenticated") {
            val user: UserModel =
                call.principal() ?: return@get call.respond(HttpStatusCode.Unauthorized)
            call.respond("User is authenticated: $user")
        }
        put("/authenticate") {

        }
    }
}