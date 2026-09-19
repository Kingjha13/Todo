package com.springpra

data class RegisterUser(val name: String,val email : String,val password : String)
data class Login(val email: String,val password: String)