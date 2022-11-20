package com.example.authentication.repository

import com.example.authentication.security.JwtConfig
import com.example.authentication.service.AuthServiceFactory
import com.example.authentication.service.registerservice.UserAuthRegisterService
import com.example.authentication.service.loginservice.LoginUserParams
import com.example.authentication.service.registerservice.CreateUserParams
import com.example.utils.BaseResponse

class UserAuthRepositoryImpl(
    private val userService: AuthServiceFactory
) : UserAuthRepository {
    override suspend fun registerUserByEmail(params: CreateUserParams): BaseResponse<Any> {

        return if (isEmailExist(params.email) || isUsernameExist(params.username)) {
            BaseResponse.ErrorResponse("this email or username already registered")
        } else {
            val user = userService.registerService.registerUserByEmail(params)
            if (user != null) {
                user.authToken = JwtConfig.instance.createAccessToken(user.id)
                BaseResponse.SuccessResponse(data = user)
            } else {
                BaseResponse.ErrorResponse()
            }
        }

    }

    override suspend fun registerUserByPhoneNumber(params: CreateUserParams): BaseResponse<Any> {

        return if (isPhoneNumberExist(params.phoneNumber) || isUsernameExist(params.username)) {
            BaseResponse.ErrorResponse("this phoneNumber or username already registered")
        } else {
            val user = userService.registerService.registerUserByPhoneNumber(params)
            if (user != null) {
                user.authToken = JwtConfig.instance.createAccessToken(user.id)
                BaseResponse.SuccessResponse(data = user)
            } else {
                BaseResponse.ErrorResponse()
            }
        }

    }

    override suspend fun isUsernameAvailable(username: String): BaseResponse<Any> {
        return if (userService.registerService.isUsernameAvailable(username)) {
            BaseResponse.SuccessResponse(data = true)
        } else {
            BaseResponse.ErrorResponse("the username is already exist")
        }
    }

    override suspend fun loginUserByEmail(params: LoginUserParams): BaseResponse<Any> {
        val user = userService.loginService.loginWithEmail(params)
        return if (user!= null){
            BaseResponse.SuccessResponse(data = user)
        }else{
            BaseResponse.ErrorResponse("the email or password is wrong")
        }
    }

    override suspend fun loginUserByPhoneNumber(params: LoginUserParams): BaseResponse<Any> {
        val user = userService.loginService.loginWithPhoneNumber(params)
        return if (user!= null){
            BaseResponse.SuccessResponse(data = user)
        }else{
            BaseResponse.ErrorResponse("the phoneNumber or password is wrong")
        }    }

    override suspend fun loginUserByUsername(params: LoginUserParams): BaseResponse<Any> {
        val user = userService.loginService.loginWithUsername(params)
        return if (user!= null){
            BaseResponse.SuccessResponse(data = user)
        }else{
            BaseResponse.ErrorResponse("the username or password is wrong")
        }    }

    private suspend fun isUsernameExist(username: String): Boolean {
        return userService.registerService.findUserByUsername(username) == null
    }

    private suspend fun isPhoneNumberExist(phoneNumber: String): Boolean {
        return userService.registerService.findUserByPhoneNumber(phoneNumber) == null
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return    userService.registerService.findUserByEmail(email) == null
    }


}