package com.muflidevs.foodapp.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.AlertDialog
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import java.time.LocalDate
import java.util.Calendar
import kotlin.io.path.Path
import kotlin.io.path.moveTo

object Custom {
    @Composable
    fun DatePicker(
        page: Page,
        onDateSelected: (Calendar) -> Unit,
        onDismiss: () -> Unit
    ) {
        val context = LocalContext.current
        val calendar = remember { Calendar.getInstance() }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var selectedDate by remember { mutableStateOf(calendar.toLocalDate()) }
            AlertDialog(
                onDismissRequest = onDismiss,
                title = {
                    Text(
                        text = when (page) {
                            Page.YEAR_ONLY -> "Pilih Tahun"
                            Page.DATE_MONTH_YEAR -> "Pilih Tanggal"
                            Page.MONTH_ONLY -> "Pilih Bulan"
                        }
                    )
                },
                text = {
                    when (page) {
                        Page.YEAR_ONLY -> {
                            Helper.YearDatePickerDropdown { year ->
                                calendar.set(Calendar.YEAR, year)
                            }
                        }

                        Page.DATE_MONTH_YEAR -> {
                            Helper.NativeDatePicker(
                                onDateSelected = { selectedDate ->
                                    calendar.timeInMillis = selectedDate.timeInMillis
                                    onDateSelected(calendar)
                                },
                                onDismiss = onDismiss
                            )
                        }

                        Page.MONTH_ONLY -> {
                            Helper.MonthDatePickerDropDown { month ->
                                calendar.set(Calendar.MONTH, month -1)
                            }
                        }
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        calendar.set(
                            selectedDate.year,
                            selectedDate.monthValue - 1,
                            selectedDate.dayOfMonth
                        )
                        onDateSelected(calendar)
                    }) {
                        Text("Pilih")
                    }
                },
                dismissButton = {
                    Button(onClick = onDismiss) {
                        Text("Batal")
                    }
                }
            )
        } else {
            LaunchedEffect(Unit) {
                showLegacyDatePickerDialog(context, page, calendar, onDateSelected, onDismiss)
            }
        }
    }

    @SuppressLint("DiscouragedApi")
    private fun showLegacyDatePickerDialog(
        context: Context,
        page: Page,
        calendar: Calendar,
        onDateSelected: (Calendar) -> Unit,
        onDismiss: () -> Unit
    ) {
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, day ->
                // Update calendar dengan tanggal yang dipilih
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                onDateSelected(calendar)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        when (page) {
            Page.YEAR_ONLY -> {
                datePickerDialog.datePicker.findViewById<View>(
                    Resources.getSystem().getIdentifier("day", "id", "android")
                )?.visibility = View.GONE
                datePickerDialog.datePicker.findViewById<View>(
                    Resources.getSystem().getIdentifier("month", "id", "android")
                )?.visibility = View.GONE
            }

            Page.MONTH_ONLY -> {
                datePickerDialog.datePicker.findViewById<View>(
                    Resources.getSystem().getIdentifier("day", "id", "android")
                )?.visibility = View.GONE
            }

            else -> {
            }
        }

        datePickerDialog.setOnDismissListener {
            onDismiss()
        }
        datePickerDialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun Calendar.toLocalDate(): LocalDate {
        return LocalDate.of(get(Calendar.YEAR), get(Calendar.MONTH) + 1, get(Calendar.DAY_OF_MONTH))
    }
}

class ArrowShape(private val tipSize: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(
        Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width - tipSize, 0f)

            lineTo(size.width, size.height / 2)
            lineTo(size.width - tipSize, size.height)

            lineTo(0f, size.height)
            lineTo(0f, 0f)
            close()
        }
    )
}
