package com.springpra

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/registerusername")
    suspend fun createUser(@Body ree : RegisterUser) : Boolean
    @POST("/signin")
    suspend fun logg(@Body login : Login) : SignInResponse
    @POST("/verify")
    suspend fun ver(@Body verify: String?) : String
    @POST("/getname")
    suspend fun getName(@Body token: String?) : String
}