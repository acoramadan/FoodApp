package com.muflidevs.foodapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ButtonDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muflidevs.foodapp.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.muflidevs.foodapp.ui.design_system.LaporanCards
import com.muflidevs.foodapp.ui.view_model.RawViewModel
import com.muflidevs.foodapp.utils.Helper

@Composable
fun LaporanScreen(
    modifier: Modifier = Modifier,
    viewModel: RawViewModel = viewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val laporanList by viewModel.sayuranList.collectAsState()
    var isClickedPengeluaran by remember { mutableStateOf(true) }
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
                .padding(8.dp)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {isClickedPengeluaran = true},
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    backgroundColor = Helper.changeButtonColorPengeluaran(isClickedPengeluaran)
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Pengeluaran")
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowUp,
                        contentDescription = "Icon dropDown",
                        tint = Color.Red
                    )
                }
            }
            VerticalDivider(
                color = Helper.isPengeluaranClicked(isClickedPengeluaran),
                thickness = 1.dp,
                modifier = Modifier.align(Alignment.CenterVertically).fillMaxHeight()
            )
            Button(
                onClick = {isClickedPengeluaran = false},
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = Helper.changeButtonColorPemasukan(isClickedPengeluaran)
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Pemasukan")
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = "Icon arrowUp",
                        tint = Color.Green
                    )
                }
            }
        }
        Divider(
            color = Helper.isPengeluaranClicked(isClickedPengeluaran),
            thickness = 3.dp,
            modifier = Modifier.padding(3.dp).padding(end = 24.dp, start = 24.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(bottom = 64.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(laporanList) { sayuran ->
                val json = URLEncoder.encode(Gson().toJson(sayuran), StandardCharsets.UTF_8.toString())
                LaporanCards(
                    sayuran = sayuran,
                    onClick = {
                        navController.navigate("detail_laporan/${json}")
                    }
                )
            }
        }
    }
}