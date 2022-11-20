package com.example.authentication.models

import com.example.authentication.database.tables.UserTable
import com.example.authentication.database.tables.UserTable.autoIncrement

data class User(
    val id : Int,
    val fullName : String,
    val userName : String,
    val email : String,
    val avatar : String,
    val password : String,
    var authToken : String? = null,
    val createAt : String
)
