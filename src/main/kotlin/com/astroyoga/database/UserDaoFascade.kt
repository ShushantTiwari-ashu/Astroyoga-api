package com.astroyoga.database

import com.astroyoga.models.HoroscopeResponse
import com.astroyoga.models.User

interface UserDaoFascade {
    suspend fun getAllUsers(): List<User>
    suspend fun getUserById(id: String): User?
    suspend fun createUser(user: User): User?
    suspend fun getTodayHoroscope(): HoroscopeResponse?
}