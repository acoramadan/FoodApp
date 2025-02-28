package com.muflidevs.foodapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.muflidevs.foodapp.ui.activity.ui.theme.FoodAppTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodAppTheme {
                LoginPage()
            }
        }
    }
}

@Composable
fun LoginPage() {

}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    FoodAppTheme {
        LoginPage()
    }
}