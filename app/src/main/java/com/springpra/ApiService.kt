package com.springpra

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    @GET("/")
    suspend fun home() : String
    @POST("/users")
    suspend fun createUser(@Body user : User): Response<Void>
    @GET("/users")
    suspend fun getUser() : List<User>
//    @POST("/logins")
//    suspend fun createLogin(@Body login : Login) : Response<Void>
    @GET("/login")
    suspend fun getLogin() : List<Resister>
    @POST("/logins")
    suspend fun getLo(@Body user : Resister) : String
    @POST("/resister")
    suspend fun doresister(@Body resister : Resister) : Boolean //Response<Void>
    @PUT("/update")
    suspend fun updateName(@Body resister : Resister) : String
//    @HTTP(method = "DELETE", path = "/delete"0 hasBody = true)
//    suspend fun deleteUser(@Body resister: Resister): String
}