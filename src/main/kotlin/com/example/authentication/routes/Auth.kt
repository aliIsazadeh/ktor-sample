package com.example.authentication.routes

import com.example.authentication.repository.UserAuthRepository
import com.example.authentication.service.loginservice.LoginUserParams
import com.example.authentication.service.registerservice.CreateUserParams
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRoutes(repository: UserAuthRepository){

    routing {
        route("/auth"){
            route("/register"){
                post("/email"){
                    val params = call.receive<CreateUserParams>()
                    val result = repository.registerUserByEmail(params)
                    call.respond(result.statusCode , result)
                }
                post("/phoneNumber"){
                    val params = call.receive<CreateUserParams>()
                    val result = repository.registerUserByPhoneNumber(params)
                    call.respond(result.statusCode , result)
                }
                post("/username") {
                    val params = call.receive<String>()
                    val result = repository.isUsernameAvailable(params)
                    call.respond(result.statusCode , result)
                }
            }
            route("/login"){
                post("/email"){
                    val params = call.receive<LoginUserParams>()
                    val result = repository.loginUserByEmail(params)
                    call.respond(result.statusCode , result)
                }
                post("/phoneNumber"){
                    val params = call.receive<LoginUserParams>()
                    val result = repository.loginUserByPhoneNumber(params)
                    call.respond(result.statusCode , result)
                }
                post("/username") {
                    val params = call.receive<LoginUserParams>()
                    val result = repository.loginUserByUsername(params)
                    call.respond(result.statusCode , result)
                }
            }
        }
    }
}