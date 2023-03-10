package com.astroyoga

import com.astroyoga.database.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.astroyoga.plugins.*
import io.ktor.locations.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
