package com.suhail.jetpackcomposehiltmvvmcoroutine.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.suhail.jetpackcomposehiltmvvmcoroutine.SCREEN_HOME
import com.suhail.jetpackcomposehiltmvvmcoroutine.SCREEN_REGISTER

@Composable
fun LoginScreen(navHostController: NavHostController) {
    Column {
        Text(text = "LoginScreen")
        Button(onClick = {navHostController.navigate(SCREEN_HOME)}){
            Text(text = "Login")
        }
    }
}