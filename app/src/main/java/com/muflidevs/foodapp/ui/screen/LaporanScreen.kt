package com.muflidevs.foodapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ButtonDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muflidevs.foodapp.R
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import com.muflidevs.foodapp.ui.design_system.LaporanCards
import com.muflidevs.foodapp.ui.theme.DarkGreen
import com.muflidevs.foodapp.ui.theme.LightYellow20
import com.muflidevs.foodapp.ui.view_model.RawViewModel

@Composable
fun LaporanScreen(modifier: Modifier = Modifier, viewModel: RawViewModel = viewModel()) {
    val context = LocalContext.current
    val laporanList by viewModel.sayuranList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadLaporanFromJson(context, R.raw.dummydatapengeluaranlaporan)
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    backgroundColor = LightYellow20
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Pengeluaran")
                    Icon(
                        imageVector = Icons.Outlined.ArrowDropDown,
                        contentDescription = "Icon dropDown"
                    )
                }
            }

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = DarkGreen
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Pemasukan")
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowUp,
                        contentDescription = "Icon arrowUp"
                    )
                }
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(laporanList) { sayuran ->
                LaporanCards(sayuran = sayuran)
            }
        }
    }
}