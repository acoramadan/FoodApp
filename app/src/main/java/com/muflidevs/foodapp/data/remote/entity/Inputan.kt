package com.muflidevs.foodapp.data.remote.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Date

@Parcelize
data class InputanResponse(
    val message: String?,
    var data: List<Sayuran>?,
    val error: String,
    val timeRequest: String?,
    val idRequest: String?
): Parcelable

data class ErrorResponse(
    val message: String?,
    val data: List<Sayuran>? = null,
    val error: String?,
    val timeRequest: Date?,
    val idRequest: String?
)


