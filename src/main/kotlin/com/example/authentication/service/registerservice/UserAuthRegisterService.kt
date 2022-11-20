package com.example.authentication.service.registerservice

import com.example.authentication.models.User
import com.example.authentication.service.UserSignAuthService
import com.example.authentication.service.registerservice.CreateUserParams

interface UserAuthRegisterService  : UserSignAuthService{

    suspend fun registerUserByEmail(params : CreateUserParams): User?
    suspend fun registerUserByPhoneNumber(params : CreateUserParams): User?

    suspend fun isUsernameAvailable(username: String): Boolean



}