package com.muflidevs.foodapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.muflidevs.foodapp.R
import com.muflidevs.foodapp.data.remote.entity.Sayuran
import com.muflidevs.foodapp.ui.nav.BottomNavigationItem
import com.muflidevs.foodapp.ui.theme.DarkGreen
import com.muflidevs.foodapp.ui.theme.FoodAppTheme
import com.muflidevs.foodapp.utils.Helper
import java.util.Calendar
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
                unselectedIcon = ImageVector.vectorResource(R.drawable.laporan_icon_btm),
                selectedIcon = ImageVector.vectorResource(R.drawable.laporan_icon_btm_fill)
            ),
            BottomNavigationItem(
                name = "masukan",
                unselectedIcon = Icons.Outlined.Add,
                selectedIcon = Icons.Filled.Add
            ),
            BottomNavigationItem(
                name = "rekap",
                unselectedIcon = ImageVector.vectorResource(R.drawable.rekapan_icon_btm),
                selectedIcon = ImageVector.vectorResource(R.drawable.rekapan_icon_btm_fill)
            ),
            BottomNavigationItem(
                name = "akun",
                unselectedIcon = Icons.Outlined.Person,
                selectedIcon = Icons.Filled.Person
            )
        )

    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentRoute by remember(navBackStackEntry) {
        mutableStateOf(navBackStackEntry?.destination?.route)
    }

    val selectedItemIndex by remember(currentRoute) {
        mutableIntStateOf(bottomNavItems.indexOfFirst { it.name == currentRoute })
    }
    var selectedYear by remember { mutableIntStateOf(Calendar.getInstance().get(Calendar.YEAR)) }
    var selectedMonth by remember { mutableIntStateOf(Calendar.getInstance().get(Calendar.MONTH)) }
    var selectedDate by remember { mutableStateOf<Calendar?>(null) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = DarkGreen
    ) {
        Scaffold(
            topBar = {
                Helper.DynamicTopAppBar(
                    currentRoute = currentRoute,
                    onYearSelected = { year -> selectedYear = year },
                    onMonthSelected = {month -> selectedMonth = month},
                    onDateSelected = {date -> selectedDate = date},
                    onDismiss = {}
                )
            },

            bottomBar = {
                NavigationBar {
                    bottomNavItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                if (currentRoute != item.name) {
                                    homeNavController.navigate(item.name) {
                                        popUpTo(homeNavController.graph.startDestinationId) {
                                            inclusive = false
                                            saveState = true
                                        }
                                        restoreState = true
                                        launchSingleTop = true
                                    }
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
            },
            contentWindowInsets = WindowInsets(bottom = 10.dp)
        ) { _ ->
            NavHost(
                navController = homeNavController,
                startDestination = "laporan",
                modifier = modifier.padding(top = 32.dp).fillMaxSize(),
                enterTransition = { fadeIn(animationSpec = tween(300)) },
                exitTransition = { fadeOut(animationSpec = tween(300)) }
            ) {
                composable("beranda") { BerandaScreen(modifier = modifier, selectedYear = selectedYear) }
                composable("laporan") { LaporanScreen(modifier = modifier, navController = homeNavController) }
                composable("masukan") { InputScreen(modifier = modifier) }
                composable("rekap") { RekapScreen(modifier = modifier) }
                composable("akun") { AkunScreen(modifier = modifier) }
                composable("detail_laporan/{sayuran}") { backStackEntry ->
                    val json = backStackEntry.arguments?.getString("sayuran")
                    val sayuran = Gson().fromJson(json, Sayuran::class.java)
                    DetailLaporanScreen(
                        sayuran = sayuran,
                        onBackPressed = { homeNavController.popBackStack() }
                    )
                }
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
