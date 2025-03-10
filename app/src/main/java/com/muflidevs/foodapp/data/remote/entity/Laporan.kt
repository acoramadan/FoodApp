package com.muflidevs.foodapp.data.remote.entity

data class Details(
    val namaPetani: String,
    val jumlahDibeli: Int,
    val totalHarga: Int
)
data class Sayuran(
    val namaSayur: String,
    val hargaSatuan: Int,
    val totalJumlahDibeli: Int,
    val totalHargaPengeluaran: Int,
    val listPetani: List<Details>
)
data class Laporan(
    var laporan: List<Sayuran>
)
