package com.muflidevs.foodapp.ui.screen

import android.graphics.Color
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.muflidevs.foodapp.R
import com.muflidevs.foodapp.ui.nav.BottomNavigationItem
import com.muflidevs.foodapp.ui.theme.DarkGreen
import com.muflidevs.foodapp.ui.theme.FoodAppTheme
import com.muflidevs.foodapp.utils.Converter
import com.muflidevs.foodapp.utils.Helper
import java.util.Calendar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {

    val homeNavController = rememberNavController()
    val bottomNavItems: List<BottomNavigationItem> =
        listOf(
            BottomNavigationItem(
                name = "beranda",
                unselectedIcon = Icons.Outlined.Home,
                selectedIcon = Icons.Filled.Home
            ),
            BottomNavigationItem(
                name = "laporan",
                unselectedIcon = Converter.convertImageToVector(R.drawable.laporan_icon_btm),
                selectedIcon = Converter.convertImageToVector(R.drawable.laporan_icon_btm_fill)
            ),
            BottomNavigationItem(
                name = "masukan",
                unselectedIcon = Icons.Outlined.Add,
                selectedIcon = Icons.Filled.Add
            ),
            BottomNavigationItem(
                name = "rekap",
                unselectedIcon = Converter.convertImageToVector(R.drawable.rekapan_icon_btm),
                selectedIcon = Converter.convertImageToVector(R.drawable.rekapan_icon_btm_fill)
            ),
            BottomNavigationItem(
                name = "akun",
                unselectedIcon = Icons.Outlined.Person,
                selectedIcon = Icons.Filled.Person
            )
        )

    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val selectedItemIndex = bottomNavItems.indexOfFirst { it.name == currentRoute }
    var selectedYear by remember { mutableStateOf(Calendar.getInstance().get(Calendar.YEAR)) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = DarkGreen
    ) {
        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            topBar = {
                Helper.DynamicTopAppBar(
                    currentRoute = currentRoute,
                    onYearSelected = { year -> selectedYear = year },
                )
            },

            bottomBar = {
                NavigationBar {
                    bottomNavItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                homeNavController.navigate(item.name) {
                                    popUpTo(homeNavController.graph.startDestinationId)
                                    launchSingleTop = true
                                }

                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.name
                                )
                            },
                            label = { Text(item.name) }
                        )
                    }
                }
            }
        ) { paddingValues ->
            NavHost(
                navController = homeNavController,
                startDestination = "beranda",
                modifier = modifier.padding(paddingValues)
            ) {
                composable("beranda") { BerandaScreen(modifier = modifier, selectedYear = selectedYear) }
                composable("laporan") { LaporanScreen(modifier = modifier) }
                composable("masukan") { InputScreen(modifier = modifier) }
                composable("rekap") { RekapScreen(modifier = modifier) }
                composable("akun") { AkunScreen(modifier = modifier) }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    FoodAppTheme {
        Scaffold(modifier = Modifier.padding()) { innerPadding ->
            HomeScreen(
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}
