package com.example.authentication.database.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object UserTable : Table("users"){

    val id = integer("id").autoIncrement()
    val fullName = varchar("full_name" , 256)
    val userName = varchar("user_name" , 256)
    val phoneNumber = varchar("phone_number" , 256)
    val email = varchar("email",256)
    val avatar= text("avatar")
    val password = text("password")
    val createAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    override val primaryKey  = PrimaryKey(id)
}