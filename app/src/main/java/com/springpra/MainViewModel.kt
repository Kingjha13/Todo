//package com.springpra
//
//import android.util.Log
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.launch
//
//class MainViewModel : ViewModel() {
//
//    var message by mutableStateOf("Loading...")
//        private set
//
//    fun getMessage() {
//        viewModelScope.launch {
//            try {
//                message = RetrofitClient.api.home()
//                Log.d("API_SUCCESS", message)
//            } catch (e: Exception) {
//                Log.e("API_ERROR", "Network Error", e)
//                message = e.toString()
//            }
//        }
//    }
//    fun setUser(name: String, age: Int){
//        viewModelScope.launch {
//            try {
//                val newUser = User(name = name , age = age)
//                val response = RetrofitClient.api.createUser(newUser)
//                if (response.isSuccessful) {
//                    message = "User $name successfully created!"
//                    Log.d("API_SUCCESS", message)
//                } else {
//                    message = "Failed to create user. Server Code: ${response.code()}"
//                    Log.e("API_ERROR", message)
//                }
//
//                Log.d("API_SUCCESS", message)
//            } catch (e: Exception) {
//                Log.e("API_ERROR", "Network Error", e)
//                message = e.toString()
//            }
//        }
//    }
//}