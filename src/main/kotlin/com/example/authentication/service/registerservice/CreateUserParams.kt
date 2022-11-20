package com.example.authentication.service.registerservice

data class CreateUserParams (
    val username : String,
    val email : String,
    val phoneNumber: String,
    val password: String
    )