package com.muflidevs.foodapp.ui.screen

import android.content.res.Resources
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.muflidevs.foodapp.R
import com.muflidevs.foodapp.ui.nav.BottomNavigationItem
import com.muflidevs.foodapp.ui.theme.FoodAppTheme
import com.muflidevs.foodapp.utils.Converter
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null
) {
    val bottomNavItems: List<BottomNavigationItem> =
        listOf(
            BottomNavigationItem(
                name = "Beranda",
                unselectedIcon = Icons.Outlined.Home,
                selectedIcon = Icons.Filled.Home
            ),
            BottomNavigationItem(
                name = "Laporan",
                unselectedIcon = Converter.convertImageToVector(R.drawable.laporan_icon_btm),
                selectedIcon = Converter.convertImageToVector(R.drawable.laporan_icon_btm_fill)
            ),
            BottomNavigationItem(
                name = "Masukan",
                unselectedIcon = Icons.Outlined.Add,
                selectedIcon = Icons.Filled.Add
            ),
            BottomNavigationItem(
                name = "Rekap",
                unselectedIcon = Converter.convertImageToVector(R.drawable.rekapan_icon_btm),
                selectedIcon = Converter.convertImageToVector(R.drawable.rekapan_icon_btm_fill)
            ),
            BottomNavigationItem(
                name = "Akun",
                unselectedIcon = Icons.Outlined.Person,
                selectedIcon = Icons.Filled.Person
            )
        )
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    bottomNavItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController?.navigate(item.name)
                            },
                            icon = {
                                Icon(
                                    imageVector =
                                        if (index == selectedItemIndex)
                                            item.selectedIcon
                                        else
                                            item.unselectedIcon,
                                    contentDescription = item.name
                                )
                            }
                        )
                    }
                }
            }
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = MaterialTheme.colorScheme.background
            ) {
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
