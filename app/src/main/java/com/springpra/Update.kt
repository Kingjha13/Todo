package com.springpra

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun Update(){
    var name by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(modifier = Modifier.padding(top = 50.dp)) {
        OutlinedTextField(value = name, onValueChange = {name = it},
            label = { Text("Enter the correct name") })
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = id, onValueChange = {id=it},
            label = {Text("Enter your unique id")})
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            scope.launch{
                try {
                    val newUser = Resister(name,id.toLong(),pass)
                    val response = RetrofitClient.api.updateName(newUser)
                    if(response.isNotEmpty()){
                        Toast.makeText(context,response,Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show()
                    }
                }
                catch (e: Exception){
                    Toast.makeText(context,"Something went wrong",Toast.LENGTH_LONG).show()
                }
            }
        }) {
            Text("Update")
        }
    }
}