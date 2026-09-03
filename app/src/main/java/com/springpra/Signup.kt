package com.springpra

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropSourceModifierNode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.nio.file.WatchEvent


@Composable
fun Signup(navController: NavController){
    var name by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(top = 22.dp)) {
        Row() {
            IconButton(onClick = {navController.navigate("home")}) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back to home")
            }
            Spacer(modifier = Modifier.width(30.dp))
            Text(text = "Create account", fontWeight = FontWeight.Bold, fontSize = 22.sp, modifier = Modifier.padding(top = 10.dp))
        }
        Text(text = "Get started", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.padding(start = 15.dp))
        EditField(name,"Full Name",{name=it})
        Spacer(modifier = Modifier.height(10.dp))
        EditField(name,"Email",{name=it})
        Spacer(modifier = Modifier.height(10.dp))
        EditField(name,"Password",{name=it})
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(onClick = {name="Avanish"}, modifier = Modifier.height(50.dp).padding(start = 20.dp, end = 20.dp).fillMaxWidth(), shape = RoundedCornerShape(10.dp)
            ,colors= ButtonDefaults.buttonColors(Color(0xFF2C8BFF))) {
            Text(text = "Create account",color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text ="By signing up you agree to our terms\n", color = Color.Gray, fontSize = 16.sp, modifier = Modifier.padding(start = 37.dp))
    }
}

@Composable
fun EditField(value : String, label : String, onValueChange : (String) -> Unit,modifier: Modifier= Modifier){
    OutlinedTextField(value=value,onValueChange=onValueChange,label={Text(text = label)},modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp), shape = RoundedCornerShape(10.dp))
}