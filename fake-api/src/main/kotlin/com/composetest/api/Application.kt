package com.composetest.api

import com.composetest.api.firebase.FirebaseAdmin
import com.composetest.api.plugins.configureFirebaseAuth
import com.composetest.api.plugins.configureRouting
import com.composetest.api.plugins.configureSecurity
import com.composetest.api.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    FirebaseAdmin.init()
    configureFirebaseAuth()
    configureSerialization()
    configureSecurity()
    configureRouting()
}
