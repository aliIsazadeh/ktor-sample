package com.example.authentication.service.loginservice

import com.example.authentication.database.DatabaseFactory
import com.example.authentication.database.DatabaseFactory.dbQuery
import com.example.authentication.database.tables.UserTable
import com.example.authentication.models.User
import com.example.authentication.security.hash
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserAuthLoginServiceImpl : UserAuthLoginService {
    override suspend fun findUserByEmail(email: String): User? {
        val user = dbQuery {
            UserTable.select{UserTable.email eq email}.mapNotNull { rowToUser(it) }.singleOrNull()
        }
        return user
    }

    override suspend fun findUserByPhoneNumber(phoneNumber: String): User? {
        val user = dbQuery {
            UserTable.select{UserTable.email.eq(phoneNumber)}
                .map { rowToUser(it) }.singleOrNull()
        }
        return user      }

    override suspend fun findUserByUsername(username: String): User? {
        val user = dbQuery {
            UserTable.select{UserTable.email.eq(username)}
                .map { rowToUser(it) }.singleOrNull()
        }
        return user        }

    override suspend fun loginWithEmail(params: LoginUserParams): User? {
        val user = findUserByEmail(params.email)
        return if (user!=null){
            if (user.password == params.password){
                user
            }else{
                null
            }
        }else{
            null
        }
    }

    override suspend fun loginWithPhoneNumber(params: LoginUserParams): User? {
        val user = findUserByPhoneNumber(params.phoneNumber)
        return if (user!=null){
            if (hash(user.password) == params.password){
                user
            }else{
                null
            }
        }else{
            null
        }    }

    override suspend fun loginWithUsername(params: LoginUserParams): User? {
        val user = findUserByUsername(params.username)
        return if (user!=null){
            if (hash(user.password) == params.password){
                user
            }else{
                null
            }
        }else{
            null
        }    }


    private fun rowToUser(row: ResultRow?): User? {
        return if (row == null) null
        else {
            User(
                id = row[UserTable.id],
                fullName = row[UserTable.fullName],
                userName = row[UserTable.userName],
                email = row[UserTable.email],
                avatar = row[UserTable.avatar],
                password = row[UserTable.password],
                createAt = row[UserTable.createAt].toString()

            )
        }
    }
}