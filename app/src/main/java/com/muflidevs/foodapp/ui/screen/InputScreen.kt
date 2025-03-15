package com.muflidevs.foodapp.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FabPosition
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FloatingActionButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muflidevs.foodapp.ui.theme.DarkGreen
import com.muflidevs.foodapp.ui.theme.LightYellow20
import com.muflidevs.foodapp.ui.view_model.RawViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.muflidevs.foodapp.R
import com.muflidevs.foodapp.ui.theme.FoodAppTheme
import com.muflidevs.foodapp.utils.Helper

@Composable
fun InputScreen(
    modifier: Modifier = Modifier,
    viewModel: RawViewModel = viewModel(),
) {
    val context = LocalContext.current
    val inputanList by viewModel.inputanResult.collectAsState()
    var isClickedPengeluaran by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.loadLaporanFromJsonInputan(context, R.raw.dummyinputan)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Log.d("FAB", "Edit button clicked")
                },
                modifier = Modifier
                    .padding(16.dp)
                    .padding(bottom = 55.dp)
                    .size(40.dp),
                backgroundColor = DarkGreen
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Edit FAB",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(paddingValues),
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
            Text(
                text = "Sayuran",
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontSize = 14.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                textAlign = TextAlign.Start
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Log.d("Input Screen", "$inputanList")
                if (inputanList?.data != null) {
                    itemsIndexed(inputanList!!.data ?: emptyList()) { index, item ->
                        Log.d("Input Screen", "$index , $item")
                        Column(
                            modifier = Modifier.padding(18.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(item.gambarUrl),
                                contentDescription = "gambar sayuran",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .border(
                                        2.dp,
                                        Helper.isPengeluaranClicked(isClickedPengeluaran),
                                        CircleShape
                                    )
                            )
                            Text(
                                text = item.namaSayur ?: "null",
                                fontSize = 10.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()

                            )
                        }
                    }
                }
            }
            Text(
                text = "Lainnya",
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontSize = 14.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InputScreenPreview(modifier: Modifier = Modifier) {
    FoodAppTheme {
        androidx.compose.material3.Scaffold(modifier = Modifier.padding()) { innerPadding ->
            InputScreen(
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

