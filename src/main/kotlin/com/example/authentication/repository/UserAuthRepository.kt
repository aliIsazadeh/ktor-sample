package com.example.authentication.repository

import com.example.authentication.service.loginservice.LoginUserParams
import com.example.authentication.service.registerservice.CreateUserParams
import com.example.utils.BaseResponse

interface UserAuthRepository {

    suspend fun registerUserByEmail(params: CreateUserParams): BaseResponse<Any>
    suspend fun registerUserByPhoneNumber(params: CreateUserParams): BaseResponse<Any>

    suspend fun isUsernameAvailable(username: String) : BaseResponse<Any>

    suspend fun loginUserByEmail(params: LoginUserParams): BaseResponse<Any>
    suspend fun loginUserByPhoneNumber(params: LoginUserParams): BaseResponse<Any>
    suspend fun loginUserByUsername(params: LoginUserParams): BaseResponse<Any>



}