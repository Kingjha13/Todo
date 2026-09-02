package com.springpra

import android.R
import android.text.style.BackgroundColorSpan
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun login(navController: NavController){
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scroleState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scroleState)) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 50.dp).height(130.dp).background(Color.Blue, shape = RoundedCornerShape(20.dp)).fillMaxWidth()){
            Text(text = "Karna kya h", color = Color.White, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Sign in", modifier = Modifier.padding(start = 20.dp), Color(0xFF000000),
            fontWeight= FontWeight.Bold, fontSize = 19.sp)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value =username,
            onValueChange = {username=it},
            placeholder = {Text("Enter Your Email")},
            label = {Text(text = "Email address")}
            , modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp), shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value =password,
            onValueChange = {password=it},
            placeholder = {Text("Enter Your Password")},
            label = {Text(text = "Password")}
            , modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp), shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedButton(onClick = {username="Avanish"}, modifier = Modifier.height(50.dp).padding(start = 20.dp, end = 20.dp).fillMaxWidth(), shape = RoundedCornerShape(10.dp)
            ,colors= ButtonDefaults.buttonColors(Color(0xFF2C8BFF))) {
            Text(text = "Sign in",color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Don't have an account?", modifier = Modifier.padding(start = 100.dp), color = Color.Gray)
        TextButton(onClick = {navController.navigate("signup")}, modifier = Modifier.padding(start = 110.dp)
        ){
            Text(text = "Create One", color = Color.Blue, fontWeight = FontWeight.Bold)
        }
    }

}

