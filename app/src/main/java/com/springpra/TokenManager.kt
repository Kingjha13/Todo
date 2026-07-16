package com.springpra

import android.content.Context

object TokenManager {
    private const val PREFS_NAME = "token"
    private const val TOKEN_KEY = "token"
    fun saveToken(context : Context, token: String) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        sharedPref.edit()
            .putString(TOKEN_KEY,token)
            .apply()
    }
    fun getToken(context: Context): String? {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(TOKEN_KEY, null)
    }
    fun clearToken(context: Context) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        sharedPref.edit()
            .remove(TOKEN_KEY)
            .apply()
    }
}