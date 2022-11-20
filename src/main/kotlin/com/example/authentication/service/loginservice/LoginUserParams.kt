package com.example.authentication.service.loginservice

import javax.print.attribute.standard.JobOriginatingUserName

data class LoginUserParams(
    val username: String,
    val email: String,
    val phoneNumber: String,
    val password: String
)