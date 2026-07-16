package com.springpra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import kotlinx.coroutines.launch
import androidx.compose.material3.Button
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            val vm : MainViewModel = viewModel()
//            HomeScreen(vm)
//            Testing()
//            Log()
//            var isRegistered by remember { mutableStateOf(false) }
//
//            if (isRegistered) {
//                Welcome()
//            } else {
//                Registration(onRegistrationSuccess = {
//                    isRegistered = true
//                })
//            }
            Welcome()
//            Registration()
//            Update()
//            Delete()
//            Logs()
        }
    }
}

//@Composable
//fun Log() {
//    var id by remember { mutableStateOf("") }
//    var pass by remember { mutableStateOf("") }
//    val scope = rememberCoroutineScope()
//    var list by remember { mutableStateOf(listOf<Login>()) }
//    var message by remember { mutableStateOf("") }
//
//    LaunchedEffect(Unit) {
//        try {
//            list = RetrofitClient.api.getLogin()
//        } catch (e: Exception) {
//            Log.e("API_ERROR", "Fetch Failed", e)
//        }
//    }
//
//    Column(modifier = Modifier.fillMaxSize().padding(top = 50.dp, start = 16.dp, end = 16.dp)) {
//        OutlinedTextField(
//            value = id,
//            onValueChange = { id = it },
//            label = { Text("Enter ID") }
//        )
//        Spacer(modifier = Modifier.height(20.dp))
//        OutlinedTextField(
//            value = pass,
//            onValueChange = { pass = it },
//            label = { Text("Enter Password") }
//        )
//        Spacer(modifier = Modifier.height(20.dp))
//        Button(
//            onClick = {
//                scope.launch {
//                    try {
//                        val newLogin = Login(id = id, pass = pass)
//                        val response = RetrofitClient.api.createLogin(newLogin)
//                        if (response.isSuccessful) {
//                            message = "Success"
//                            Log.d("API_SUCCESS", "Logged In")
//                        } else {
//                            message = "Fail: ${response.code()}"
//                            Log.e("API_ERROR", "Failed")
//                        }
//                    } catch (e: Exception) {
//                        message = e.toString()
//                        Log.e("API_ERROR", "Exception", e)
//                    }
//                }
//            }
//        ) {
//            Text("Submit")
//        }
//        Spacer(modifier = Modifier.height(20.dp))
//        Text(text = message)
//        Spacer(modifier = Modifier.height(20.dp))
//
//        var userFound = false
//        list.forEach {
//            if (it.id == id && it.pass == pass) {
//                userFound = true
//            }
//        }
//
//        if (id.isNotEmpty() && pass.isNotEmpty()) {
//            if (userFound) {
//                Text("Match Found", color = MaterialTheme.colorScheme.primary)
//            } else {
//                Text("No Match", color = MaterialTheme.colorScheme.error)
//            }
//        }
//    }
//}

@Composable
fun Testing(){
    val scope = rememberCoroutineScope()
    var mess by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var ageInput by remember { mutableStateOf("") }

    var age: Int? = ageInput.toIntOrNull() ?: 0
    LaunchedEffect(Unit) {
        mess = RetrofitClient.api.home()
    }
    Column(modifier = Modifier.padding(top = 50.dp)) {
        Text(mess)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = name , onValueChange = {name = it}, modifier = Modifier.padding(16.dp))
        OutlinedTextField(
            value = ageInput,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    ageInput = newValue
                }
            },
            modifier = Modifier.padding(16.dp)
        )
        val newUser = User(name = name, age = age)
        Button({
            scope.launch{
                val response = RetrofitClient.api.createUser(newUser)
                if (response.isSuccessful) {
                    mess = "User $name successfully created!"
                } else {
                    mess = "Failed to create user. Server Code: ${response.code()}"
                }
            }
        }) {
            Text("Submit")
        }
        var list by remember { mutableStateOf(listOf<User>()) }
        LaunchedEffect(Unit) {
            list = RetrofitClient.api.getUser()
        }
        list.forEach {
            Text(it.name)
            Text(it.age.toString())
        }
        Button({
            scope.launch{
                list = RetrofitClient.api.getUser()
            }
        }) {
            Text("Get Users")
        }
    }

}
//@Composable
//fun HomeScreen(vm : MainViewModel){
//    var name by remember { mutableStateOf("") }
//    var age  by remember { mutableIntStateOf(0) }
//    LaunchedEffect(Unit){
//        vm.getMessage()
//    }
//
//
//    Column(modifier = Modifier.fillMaxSize() . padding(top = 50.dp)) {
//        Text(vm.message)
//        OutlinedTextField(value = name,
//            onValueChange = {newValue -> name =newValue},
//            label = {Text("Enter your name")},
//            placeholder = {Text("avanish")}
//        )
//        Spacer(modifier = Modifier.height(20.dp))
//        OutlinedTextField(
//            value = if (age == 0) "" else age.toString(),
//
//            onValueChange = { newValue ->
//                age = newValue.toIntOrNull() ?: 0
//            },
//            label = { Text("Enter your age") },
//            placeholder = { Text("17") }
//        )
//        Button(
//            onClick = {
//                vm.setUser(name, age)
//            }
//        ) {
//            Text("Submit")
//        }
//        Text(name)
//        Text(age.toString())
//    }
//}