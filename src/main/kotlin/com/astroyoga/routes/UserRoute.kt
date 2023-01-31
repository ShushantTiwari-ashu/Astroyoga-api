@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.astroyoga.routes

import com.astroyoga.database.UserDaoFascadeImpl
import com.astroyoga.models.User
import com.astroyoga.models.UserDataResponse
import com.astroyoga.utils.CREATE
import com.astroyoga.utils.USER
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


@Location(CREATE)
class UserCreateRoute

@Location(USER)
class UserRoute

fun Route.users(
    db: UserDaoFascadeImpl,
) {
    post(CREATE) {
        val user = kotlin.runCatching { call.receive<User>() }.getOrElse {
            throw BadRequestException(it.localizedMessage)
        }
        try {
            val newUser = db.createUser(user)
            newUser?.let {
                call.respond(UserDataResponse.success(it, "Success"))
            }
        } catch (e: Throwable) {
            application.log.error("Failed to register user", e)
            call.respond(HttpStatusCode.BadRequest, "Problems creating User")
        }
    }

}

