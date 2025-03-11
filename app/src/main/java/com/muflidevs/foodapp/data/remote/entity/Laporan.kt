package com.muflidevs.foodapp.data.remote.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Details(
    val namaPetani: String,
    val jumlahDibeli: Int,
    val totalHarga: Int
): Parcelable

@Parcelize
data class Sayuran(
    val id: Int,
    val namaSayur: String,
    val hargaSatuan: Int,
    val totalJumlahDibeli: Int,
    val totalHargaPengeluaran: Int,
    val listPetani: List<Details>
): Parcelable

@Parcelize
data class Laporan(
    var laporan: List<Sayuran>
) : Parcelable
