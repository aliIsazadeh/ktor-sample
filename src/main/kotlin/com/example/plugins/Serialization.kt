package com.example.plugins

import io.ktor.serialization.kotlinx.json.gson.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.jackson.*
import com.fasterxml.jackson.databind.*
import io.ktor.server.plugins.*
import io.ktor.serialization.kotlinx.json.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
        }
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
        json()
    }

    routing {
        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }
        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }
        get("/json/kotlinx-serialization") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}
