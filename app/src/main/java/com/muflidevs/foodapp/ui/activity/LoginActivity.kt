package com.muflidevs.foodapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.muflidevs.foodapp.ui.screen.LoginScreen
import com.muflidevs.foodapp.ui.theme.FoodAppTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginPage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun LoginPage(modifier: Modifier) {
    LoginScreen(modifier = modifier)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPagePreview() {
    FoodAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            LoginPage(modifier = Modifier.padding(innerPadding))
        }
    }
}