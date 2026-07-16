package com.springpra

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun Welcome(){
    val context = LocalContext.current
    val token = TokenManager.getToken(context)
    var responseId by remember { mutableStateOf("loading.....") }
    val emptyList = remember { mutableListOf<String>() }
    var str by remember {
        mutableStateOf("Loading...")
    }
//    LaunchedEffect(Unit) {
//        try {
//            val apiResponse = RetrofitClient.api.getLogin()
//
//            emptyList.clear()
//
//            for (item in apiResponse) {
//                emptyList.add(item.name)
//            }
//
//            if (emptyList.isNotEmpty()) {
//                responseId = "Data Loaded Successfully"
//            }
//        }
//        catch (e: Exception) {
//            responseId = "Error fetching data"
//        }
//    }
    LaunchedEffect(Unit) {
        str = try {
            RetrofitClient.api.getMe(token!!)
        } catch (e : Exception){
            "Error"
        }
    }
    Column(modifier = Modifier.fillMaxSize().padding(top = 20.dp)) {
//        Text(text = "Welcome to the app. ID: $responseId")
//        for (ite in emptyList){
//            Text(text = ite)
//        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = str)
    }
}

@Composable
fun Login(onLogin: Boolean){
    var username = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().padding(top = 20.dp)) {
        OutlinedTextField(value = username.value, onValueChange = {username.value = it}, label = {Text("Enter your username")})
        OutlinedTextField(value = password.value, onValueChange = {password.value = it}, label = {Text("Enter your password")})
        if (username.equals("ava") && password.equals("ava")){
            Text("Login Successful")
        }
        else{
            Text("Login Failed")
        }
    }
}