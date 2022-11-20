package com.example

import com.example.authentication.database.DatabaseFactory
import com.example.authentication.repository.UserAuthRepository
import com.example.authentication.repository.UserAuthRepositoryImpl
import com.example.authentication.routes.authRoutes
import com.example.authentication.security.configureSecurity
import com.example.authentication.service.AuthServiceFactory
import com.example.authentication.service.loginservice.UserAuthLoginService
import com.example.authentication.service.loginservice.UserAuthLoginServiceImpl
import com.example.authentication.service.registerservice.UserAuthRegisterService
import com.example.authentication.service.registerservice.UserAuthRegisterServiceImpl
import io.ktor.serialization.jackson.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import org.slf4j.*
import java.io.*

fun main() {

//    val environment = applicationEngineEnvironment {
//        log = LoggerFactory.getLogger("ktor.application")
//        connector {
//            port = 8080
//        }
//        sslConnector(
//            keyStore = keystore,
//            keyAlias = "sampleAlias",
//            keyStorePassword = { "foobar".toCharArray() },
//            privateKeyPassword = { "foobar".toCharArray() }) {
//            port = 8443
//            keyStorePath = keyStoreFile
//        }
//        module(Application::module)
//    }

    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        DatabaseFactory.init()
//        configureSerialization()
//        configureSockets()
//        configureRouting()
         install(ContentNegotiation){
             jackson()
         }
        configureSecurity()
        val loginService : UserAuthLoginService = UserAuthLoginServiceImpl()
        val registerService : UserAuthRegisterService = UserAuthRegisterServiceImpl()
        val authService: AuthServiceFactory = AuthServiceFactory(loginService = loginService , registerService = registerService )
        val authRepository : UserAuthRepository = UserAuthRepositoryImpl(authService)
        authRoutes(authRepository)

    }.start(wait = true)
}
