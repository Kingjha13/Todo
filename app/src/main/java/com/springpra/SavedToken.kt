package com.springpra

import android.content.Context

fun savedTokenStorage(context : Context,token : String){
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val editor=sharedPreferences.edit()
    editor.putString("auth_token",token)
    editor.apply()
}

fun  getTokenStorage(context : Context) : String?{
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("auth_token",null)
}