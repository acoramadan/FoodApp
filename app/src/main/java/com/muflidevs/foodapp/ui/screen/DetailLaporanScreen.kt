package com.muflidevs.foodapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.muflidevs.foodapp.data.remote.entity.Sayuran
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.text.font.FontWeight

@Composable
fun DetailLaporanScreen(
    sayuran: Sayuran,
    onBackPressed: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Detail Laporan",
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Nama Sayuran: ${sayuran.namaSayur}",
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "Harga Satuan: Rp${sayuran.hargaSatuan}")
                        Text(text = "Total Jumlah Dibeli: ${sayuran.totalJumlahDibeli}")
                        Text(text = "Total Harga Pengeluaran: Rp${sayuran.totalHargaPengeluaran}")
                    }
                }
            }

            item {
                Text(
                    text = "Daftar Petani",
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            if (sayuran.listPetani != null) {
                items(sayuran.listPetani) { petani ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                            .padding(8.dp)
                            .background(Color(0xFFF8F8F8))
                    ) {
                        Text(
                            text = "Nama Petani: ${petani.namaPetani}",
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "Jumlah Dibeli: ${petani.jumlahDibeli}")
                        Text(text = "Total Harga: Rp${petani.totalHarga}")
                    }
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
        Button(
            onClick = onBackPressed,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Kembali")
        }
    }
}
