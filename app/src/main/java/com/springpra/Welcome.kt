package com.springpra

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun Welcome(navController: NavController){
    Column(modifier = Modifier.padding(top = 40.dp)) {
        var name by remember { mutableStateOf("Loading...") }
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            name = try {
                RetrofitClient.api.getName(getTokenStorage(context))
            } catch (e : Exception){
                "see the issues"
            }
        }
        Text(text = "Welcome $name", modifier = Modifier.background(Color.Blue).fillMaxWidth(), fontWeight = FontWeight.Bold, fontSize = 30.sp, color = Color.Cyan)

    }
}