package com.example.authentication.service

import com.example.authentication.service.loginservice.UserAuthLoginService
import com.example.authentication.service.registerservice.UserAuthRegisterService

class AuthServiceFactory (
    val loginService : UserAuthLoginService,
    val registerService : UserAuthRegisterService
)