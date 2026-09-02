package com.springpra

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.EncodeDefault

@Composable
fun Get(){
    var str by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        str = try {
            RetrofitClient.api.gettest("ava")
        } catch (e : Exception){
            "Error"
        }
    }
    Column(modifier = Modifier.padding(top = 30.dp)) {
        Text(text = str)
    }
}