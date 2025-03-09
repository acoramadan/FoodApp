package com.muflidevs.foodapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muflidevs.foodapp.R
import com.muflidevs.foodapp.ui.design_system.BarchartWithSolidBars


@Composable
fun BerandaScreen(
    modifier: Modifier = Modifier,
    selectedYear: Int? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp),

        ) {
        Column(
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        ) {
            Text(
                text = "Hi User",
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontSize = 18.sp,
            )
            Text(
                text = "Berikut adalah grafik " +
                        "yang menampilkan\nkeuntungan " +
                        "anda tahun ini",
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        }
        BarchartWithSolidBars()
    }
}
