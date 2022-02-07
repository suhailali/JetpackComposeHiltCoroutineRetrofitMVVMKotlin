package com.suhail.jetpackcomposehiltmvvmcoroutine.commonui

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun CircleProgressBar() {
    CircularProgressIndicator(color = MaterialTheme.colors.primary)
}