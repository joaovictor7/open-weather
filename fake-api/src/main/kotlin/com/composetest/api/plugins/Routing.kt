package com.composetest.api.plugins

import com.composetest.api.routes.configureAuthenticationRouting
import com.composetest.api.routes.configureRootRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        configureRootRouting()
        configureAuthenticationRouting()
    }
}