package com.springpra

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun Signup(navController: NavController){
    Text(text = "Welcome to signup screen", modifier = Modifier.padding(top = 40.dp))
}