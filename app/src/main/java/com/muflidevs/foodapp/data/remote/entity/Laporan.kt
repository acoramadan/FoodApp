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
data class Laporan(
    var laporan: List<Sayuran>
) : Parcelable
