package com.example.authentication.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

class JwtConfig private constructor(secret : String) {

    companion object{
        private const val ISSUER = "ktor_book_sample"
        private const val AUDIENCE = "ktor_book_sample"
        const val CLAIM = "id"

        lateinit var instance : JwtConfig
        private set

        fun initialize( secret: String){
            synchronized(this){
                if(!this::instance.isInitialized){
                    instance = JwtConfig(secret)
                }
            }
        }
    }

    private val algorithm = Algorithm.HMAC256(secret)

    val verifier : JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(AUDIENCE)
        .build()

    fun createAccessToken(id : Int): String = JWT
        .create()
        .withAudience(ISSUER)
        .withAudience(AUDIENCE)
        .withClaim(CLAIM,id)
        .sign(algorithm)




}