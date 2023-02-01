package com.astroyoga.database

import com.astroyoga.table.HoroscopeTable
import com.astroyoga.table.UserTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init() {
        val tables = arrayOf(UserTable, HoroscopeTable)
        Database.connect(hikari())
        transaction {
            SchemaUtils.createMissingTablesAndColumns(*tables)
        }
    }


    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = System.getenv("JDBC_DRIVER")
        config.jdbcUrl = System.getenv("JDBC_DATABASE_URL")
        config.maximumPoolSize = 10
        /* config.username ="shushant"
         config.password = "Ashu@8859"*/
        config.isAutoCommit = true
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T = kotlin.runCatching {
        return@runCatching withContext(Dispatchers.IO) {
            transaction {
                block()
            }
        }
    }.getOrElse { throw it }
}