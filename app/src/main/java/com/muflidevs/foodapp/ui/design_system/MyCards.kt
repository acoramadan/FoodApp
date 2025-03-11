package com.muflidevs.foodapp.ui.design_system

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muflidevs.foodapp.R
import com.muflidevs.foodapp.data.remote.entity.Sayuran

@Composable
fun LaporanCards(sayuran: Sayuran,onClick: () -> Unit?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable{ onClick() },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "icon details",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = sayuran.namaSayur,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 12.sp
                    )
                    Text(
                        text = "Rp${sayuran.hargaSatuan}",
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 12.sp
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${sayuran.totalJumlahDibeli} kg",
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 12.sp
                    )
                    Text(
                        text = "-Rp${sayuran.totalHargaPengeluaran}",
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}