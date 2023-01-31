package com.astroyoga.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val gender: String,
    val deviceId: String? = "",
    val username: String,
    val sentimentalStatus: String,
    val dob: String,
    val tob: String,
    val pob: String?,
    val handReadingData: String?,
    val filledIndex: Int?,
    val zodiacSign: String?,
)

