package com.springpra

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

@Composable
fun Registration(onRegistrationSuccess: () -> Unit){
    var name by remember { mutableStateOf("") }
    var id  by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize().padding(top = 40.dp)) {
        OutlinedTextField(value = name,
            onValueChange = { newValue -> name = newValue }, label = { Text("Enter your name") },
            placeholder = {Text("king")})
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = id,
            onValueChange = { newValue ->
                id = newValue
            },
            label = { Text("Enter your id") },
            placeholder = { Text("123") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = pass,
            onValueChange = {newValue -> pass = newValue},
            label = {Text("Enter your unique pass")})
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            scope.launch{
                    try {
                        val newll = Resister(name,id.toLong(),pass)
                        val response = RetrofitClient.api.doresister(newll)
                        if(response){
                            Toast.makeText(context,"Welcome $name", Toast.LENGTH_LONG).show()
                            onRegistrationSuccess()
                        }
                        else{
                            Toast.makeText(context,"User already exist with $id", Toast.LENGTH_LONG).show()
                        }
                    }
                    catch (e: Exception) {
                        Toast.makeText(context,"Fail to resister", Toast.LENGTH_LONG).show()
                    }
            }
        }) {
            Text("Submit")
        }
    }
}