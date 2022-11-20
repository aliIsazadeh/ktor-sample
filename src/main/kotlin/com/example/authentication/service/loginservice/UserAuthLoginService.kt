package com.example.authentication.service.loginservice

import com.example.authentication.models.User
import com.example.authentication.service.UserSignAuthService

interface UserAuthLoginService : UserSignAuthService {

    suspend fun loginWithEmail(params: LoginUserParams): User?
    suspend fun loginWithPhoneNumber(params: LoginUserParams): User?
    suspend fun loginWithUsername(params: LoginUserParams): User?
}