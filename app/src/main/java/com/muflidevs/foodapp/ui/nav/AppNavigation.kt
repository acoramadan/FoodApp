package com.muflidevs.foodapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.muflidevs.foodapp.ui.screen.DetailLaporanScreen
import com.muflidevs.foodapp.ui.screen.HomeScreen
import com.muflidevs.foodapp.ui.screen.LoginScreen
import com.muflidevs.foodapp.ui.screen.RegisterScreen
import com.muflidevs.foodapp.ui.screen.SplashScreen

@Composable
fun AppNavigation(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("splash") { SplashScreen(modifier = modifier, navController = navController) }
        composable("login") { LoginScreen(modifier = modifier, navController = navController) }
        composable("register") {
            RegisterScreen(
                modifier = modifier,
                navController = navController
            )
        }
        composable("home") { HomeScreen(modifier = modifier) }
    }
}