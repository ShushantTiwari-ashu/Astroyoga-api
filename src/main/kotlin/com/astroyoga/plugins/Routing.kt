package com.astroyoga.plugins

import com.astroyoga.database.UserDaoFascadeImpl
import com.astroyoga.routes.horoscope
import com.astroyoga.routes.users
import com.astroyoga.service.HoroscopeServiceImpl
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val daoFascadeImpl = UserDaoFascadeImpl()
    val horoscopeService = HoroscopeServiceImpl()
    routing {
        get("/") {
            call.respondText {
                "Hell0 astroyoga"
            }
        }
        users(daoFascadeImpl,horoscopeService)
        horoscope(daoFascadeImpl,horoscopeService)
    }
}
