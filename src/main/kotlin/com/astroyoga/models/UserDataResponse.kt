package com.astroyoga.models

import kotlinx.serialization.Serializable

@Serializable
data class UserDataResponse(
    override val status: State,
    override val message: String,
    val data: User? = null
) : Response {

    companion object {

        fun failed(message: String) = UserDataResponse(
            State.FAILED,
            message
        )

        fun unauthorized(message: String) = UserDataResponse(
            State.UNAUTHORIZED,
            message
        )

        fun success(data: User?, message: String) = UserDataResponse(
            State.SUCCESS,
            message,
            data
        )
    }
}