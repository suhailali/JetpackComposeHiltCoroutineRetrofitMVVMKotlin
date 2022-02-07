package com.suhail.jetpackcomposehiltmvvmcoroutine.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.suhail.jetpackcomposehiltmvvmcoroutine.R
import com.suhail.jetpackcomposehiltmvvmcoroutine.MainActivity
import com.suhail.jetpackcomposehiltmvvmcoroutine.ui.theme.AppTheme
import com.suhail.jetpackcomposehiltmvvmcoroutine.ui.theme.primaryColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    private var job: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Splash(stringResource(id = R.string.app_name))
                }
            }
        }
    }

    private fun navigate() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        job = CoroutineScope(Dispatchers.Main + Job()).launch {
            delay(100)
            withContext(Dispatchers.Main) {
                navigate()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        job?.let {
            it.cancel()
        }
    }
}

@Composable
fun Splash(name: String) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = primaryColor)
    )
    {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = name
            )
            Text(text = name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        Splash("Android")
    }
}