package com.example.authentication.service

import com.example.authentication.models.User

interface UserSignAuthService {
    suspend fun findUserByEmail(email: String): User?
    suspend fun findUserByPhoneNumber(phoneNumber: String): User?
    suspend fun findUserByUsername(username : String): User?
}