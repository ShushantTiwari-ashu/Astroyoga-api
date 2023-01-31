package com.astroyoga.database

import com.astroyoga.database.DatabaseFactory.dbQuery
import com.astroyoga.models.HoroscopeResponse
import com.astroyoga.models.User
import com.astroyoga.table.UserTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.util.*

class UserDaoFascadeImpl : UserDaoFascade {
    override suspend fun getAllUsers(): List<User> = emptyList()

    override suspend fun getUserById(id: String): User? {
        return dbQuery {
            UserTable.select { UserTable.device_id.eq(UUID.fromString(id)) }.map { rowToResult(it) }.singleOrNull()
        }
    }

    override suspend fun createUser(user: User): User? {
        return dbQuery {
            UserTable.insert {
                it[username] = user.username
                it[filledIndex] = user.filledIndex ?: 0
                it[device_id] = UUID.randomUUID()
                it[gender] = user.gender
                it[sentimentalStatus] = user.sentimentalStatus
                it[dob] = user.dob
                it[handReadingData] = user.handReadingData ?: ""
                it[tob] = user.tob
                it[zodiacSign] = user.zodiacSign ?: ""
                it[pob] = user.pob ?: ""
            }.resultedValues?.singleOrNull()?.let(::rowToResult)
        }
    }

    private fun rowToResult(row: ResultRow?): User? {
        if (row == null) return null
        return User(
            deviceId = row[UserTable.device_id].toString(),
            username = row[UserTable.username],
            filledIndex = row[UserTable.filledIndex],
            gender = row[UserTable.gender],
            handReadingData = row[UserTable.handReadingData],
            zodiacSign = row[UserTable.zodiacSign],
            pob = row[UserTable.pob],
            tob = row[UserTable.tob],
            dob = row[UserTable.dob],
            sentimentalStatus = row[UserTable.sentimentalStatus],
        )
    }

    override suspend fun getTodayHoroscope(): HoroscopeResponse? = null

}