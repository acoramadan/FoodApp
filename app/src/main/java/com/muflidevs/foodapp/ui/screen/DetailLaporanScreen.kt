package com.muflidevs.foodapp.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muflidevs.foodapp.ui.view_model.RawViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.muflidevs.foodapp.data.remote.entity.Sayuran

@Composable
fun DetailLaporanScreen(
    sayuran: Sayuran,
    onBackPressed: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Detail Laporan",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(text = "Nama Sayuran: ${sayuran?.namaSayur}")
        Text(text = "Harga Satuan: ${sayuran?.hargaSatuan}")
        Text(text = "Total Jumlah Dibeli: ${sayuran?.totalJumlahDibeli}")
        Text(text = "Total Harga Pengeluaran: ${sayuran?.totalHargaPengeluaran}")
        sayuran?.listPetani?.forEach { petani ->
            Text(text = "Nama Petani: ${petani.namaPetani}")
            Text(text = "Jumlah Dibeli: ${petani.jumlahDibeli}")
            Text(text = "total Harga: ${petani.totalHarga}")
        }
    }
    Button(
        onClick = onBackPressed,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text("Kembali")
    }
}
