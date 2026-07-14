package com.springpra

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable

fun Logs(){
    var name by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(modifier = Modifier.padding(top = 30.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = {newValue -> name = newValue},
    label = {Text("Enter your name")},)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = id,
            onValueChange = {nev -> id = nev},
            label = {Text("Enter your id")}
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = pass,
            onValueChange = {newValue -> pass = newValue},
            label = {Text("Enter your pass")}
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            scope.launch {
                try {
                    val re = id.toLongOrNull()
                    val newn = Resister(name,re,pass)
                    val response = RetrofitClient.api.getLo(newn)
                    Toast.makeText(context,response,Toast.LENGTH_LONG).show()
                }
                catch (e: Exception) {
                    Log.e("API_ERROR", "Exception", e)
                }
            }
        }) {
            Text("Login")
        }
    }
}