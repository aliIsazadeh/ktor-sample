package com.example.authentication.service.registerservice

import com.example.authentication.models.User
import org.jetbrains.exposed.sql.statements.InsertStatement
import com.example.authentication.database.DatabaseFactory.dbQuery
import com.example.authentication.database.tables.UserTable
import com.example.authentication.security.hash
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.time.LocalDateTime

class UserAuthRegisterServiceImpl : UserAuthRegisterService {


    override suspend fun registerUserByEmail(params: CreateUserParams): User? {

        var statement: InsertStatement<Number>? = null

        dbQuery {
            statement = UserTable.insert {
                it[email] = params.email
                it[password] = hash(params.password)
                it[userName] = params.username
                it[phoneNumber] = ""
                it[avatar]=""
                it[createAt] = LocalDateTime.now()
                it[fullName]=""
            }

        }
        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun registerUserByPhoneNumber(params: CreateUserParams): User? {

        var statement: InsertStatement<Number>? = null

        dbQuery {
            statement = UserTable.insert {
                it[phoneNumber] = params.phoneNumber
                it[password] = hash(params.password)
                it[userName] = params.username
                it[email] = ""
                it[avatar]=""
                it[createAt] = LocalDateTime.now()
                it[fullName]=""
            }
        }
        return rowToUser(statement?.resultedValues?.get(0))

    }

    override suspend fun isUsernameAvailable(username: String): Boolean {
        return findUserByUsername(username) != null
    }

    override suspend fun findUserByEmail(email: String): User? {
        val user = dbQuery {
            UserTable.select{UserTable.email.eq(email)}
                .map { rowToUser(it) }.singleOrNull()
        }
        return user
    }

    override suspend fun findUserByPhoneNumber(phoneNumber: String): User? {
        val user = dbQuery {
            UserTable.select{UserTable.email.eq(phoneNumber)}
                .map { rowToUser(it) }.singleOrNull()
        }
        return user
    }

    override suspend fun findUserByUsername(username: String): User? {
        val user = dbQuery {
            UserTable.select{UserTable.email.eq(username)}
                .map { rowToUser(it) }.singleOrNull()
        }
        return user
    }


    private fun rowToUser(row: ResultRow?): User? {
        return if (row == null) null
        else {
            User(
                id = row[UserTable.id],
                fullName = row[UserTable.email],
                userName = row[UserTable.phoneNumber],
                email = row[UserTable.userName],
                avatar = row[UserTable.avatar],
                password = row[UserTable.fullName],
                createAt = row[UserTable.createAt].toString()

            )
        }
    }
}
