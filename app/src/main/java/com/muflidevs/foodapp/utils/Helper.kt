package com.muflidevs.foodapp.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DropdownMenu
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DropdownMenuItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import java.util.Calendar


object Helper {
    private fun millisToCalendar(millis: Long): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        return calendar
    }

    private fun getDynamicYears(rangeBefore: Int = 50, rangeAfter: Int = 50): List<Int> {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        return (currentYear - rangeBefore..currentYear + rangeAfter).toList()
    }

    private fun getMonths(): List<String> = listOf<String>(
        "Januari", "Februari", "Maret",
        "April", "Mei", "Juni",
        "Juli", "Agustus", "September",
        "Oktober", "November", "Desember"
    )

    @Composable
    fun YearDatePickerDropdown(onYearSelected: (Int) -> Unit) {
        var expanded by remember { mutableStateOf(false) }
        var selectedYear by remember {
            mutableIntStateOf(
                Calendar.getInstance().get(Calendar.YEAR)
            )
        }

        val years = remember { getDynamicYears() }

        Box(
            modifier = Modifier.wrapContentSize()
        ) {
            Button(onClick = { expanded = true }) {
                Text("$selectedYear")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                years.forEach { year ->
                    DropdownMenuItem(
                        onClick = {
                            selectedYear = year
                            onYearSelected(selectedYear)
                            expanded = false
                        }
                    ) {
                        Text("$year")
                    }
                }
            }
        }
    }

    @Composable
    fun MonthDatePickerDropDown(onMonthSelected: (Int) -> Unit) {
        var expanded by remember { mutableStateOf(false) }
        var selectedMonth by remember { mutableStateOf(1) }

        val months = getMonths()

        Box(modifier = Modifier.wrapContentSize()) {
            Button(onClick = { expanded = true }) {
                Text(months[selectedMonth - 1])
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                months.forEachIndexed { index, month ->
                    DropdownMenuItem(
                        onClick = {
                            selectedMonth = index + 1
                            onMonthSelected(selectedMonth)
                            expanded = false
                        }
                    ) {
                        Text(month)
                    }
                }
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NativeDatePicker(onDateSelected: (Calendar) -> Unit, onDismiss: () -> Unit) {
        val datePickerState = rememberDatePickerState()
        var showDialog by remember { mutableStateOf(true) }

        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = onDismiss,
                confirmButton = {
                    Button(onClick = {
                        val selectedDateMillis = datePickerState.selectedDateMillis
                        if (selectedDateMillis != null) {
                            val calendar = millisToCalendar(selectedDateMillis)
                            onDateSelected(calendar)
                        }
                        showDialog = false
                    }) {
                        Text("Pilih")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        showDialog = false
                        onDismiss()
                    }) {
                        Text("Batal")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}