package com.muflidevs.foodapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CutCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ButtonDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DropdownMenu
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DropdownMenuItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.KeyboardArrowDown
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muflidevs.foodapp.R
import com.muflidevs.foodapp.data.remote.entity.Sayuran
import com.muflidevs.foodapp.ui.theme.DarkGreen
import com.muflidevs.foodapp.ui.theme.LightGreen
import com.muflidevs.foodapp.ui.theme.ModerateGreen


import java.util.Calendar


object Helper {
    private fun millisToCalendar(millis: Long): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        return calendar
    }

    private fun getDynamicYears(rangeBefore: Int = 20): List<Int> {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        return (currentYear - rangeBefore..currentYear).toList()
    }

    private fun formatDate(calendar: Calendar): String {
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1 // +1 karena bulan dimulai dari 0
        val year = calendar.get(Calendar.YEAR)
        return "$day/$month/$year" // Format: DD/MM/YYYY
    }

    fun getMonths(): List<String> = listOf<String>(
        "Januari", "Februari", "Maret",
        "April", "Mei", "Juni",
        "Juli", "Agustus", "September",
        "Oktober", "November", "Desember"
    )

    @Composable
    fun RandomCardColor(modifier: Modifier = Modifier, total: Int): Color {
        if (total < 0) {
            return Color.Transparent
        }
        if (total >= 1 && total <= 50) {
            return Color.White
        }
        else if (total  > 50 && total <= 65) {
            return LightGreen
        }
        else if (total  > 65 && total <= 100) {
            return ModerateGreen
        } else {
            return DarkGreen
        }
    }
    @Composable
    fun DynamicTopAppBar(
        currentRoute: String?,
        onYearSelected: (Int) -> Unit?,
        onMonthSelected: (Int) -> Unit?,
        onDismiss: (() -> Unit)?,
        onDateSelected: ((Calendar) -> Unit)?
    ) {
        when (currentRoute) {
            "beranda" -> {
                TopAppBar(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxWidth(),
                    backgroundColor = DarkGreen,
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(DarkGreen)
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            YearDatePickerDropdown(onYearSelected = onYearSelected)
                        }
                    }
                )
            }
            "detail_laporan/{sayuran}" -> {
                TopAppBar(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxWidth(),
                    backgroundColor = DarkGreen,
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(DarkGreen)
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = "Icon back",
                                modifier = Modifier.align(alignment = Alignment.CenterVertically)
                            )
                            Text("Detail Laporan")
                        }
                    }
                )
            }
            "masukan" -> {
                TopAppBar(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxWidth(),
                    backgroundColor = DarkGreen,
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(DarkGreen)
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            NativeDatePicker(onDateSelected = onDateSelected, onDismiss = onDismiss)
                        }
                    }
                )
            }

            "laporan" -> {
                TopAppBar(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxWidth(),
                    backgroundColor = DarkGreen,
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(DarkGreen)
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            NativeDatePicker(onDateSelected = onDateSelected, onDismiss = onDismiss)
                        }
                    }
                )
            }

            "rekap" -> {
                TopAppBar(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxWidth(),
                    backgroundColor = DarkGreen,
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(DarkGreen)
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            MonthDatePickerDropDown(onMonthSelected = onMonthSelected)
                        }
                    }
                )
            }

            "akun" -> {
                TopAppBar(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxWidth(),
                    backgroundColor = DarkGreen,
                    title = {
                        Text("Akun")
                    }
                )
            }

            else -> {
                TopAppBar(
                    modifier = Modifier.statusBarsPadding(),
                    title = {
                        Text("Default Title")
                    }
                )
            }
        }
    }

    @Composable
    fun YearDatePickerDropdown(onYearSelected: (Int) -> Unit?) {
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
            Button(
                onClick = { expanded = true },
                modifier = Modifier
                    .width(150.dp)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = CutCornerShape(3.dp)
                    ),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = DarkGreen
                ),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar_icon),
                        contentDescription = "calendar",
                        modifier = Modifier.size(12.dp)
                    )
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()) {
                        Text(
                            text = "$selectedYear",
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 11.sp
                        )
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowDown,
                            contentDescription = "calendar",
                            modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.CenterEnd)
                        )
                    }
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth(0.5f)
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
    fun MonthDatePickerDropDown(onMonthSelected: (Int) -> Unit?) {
        var expanded by remember { mutableStateOf(false) }
        var selectedMonth by remember { mutableIntStateOf(1) }

        val months = getMonths()

        Box(modifier = Modifier.wrapContentSize()) {
            Button(
                onClick = { expanded = true },
                modifier = Modifier
                    .width(150.dp)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = CutCornerShape(3.dp)
                    ),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = DarkGreen
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar_icon),
                        contentDescription = "calendar",
                        modifier = Modifier.size(12.dp)
                    )
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()) {
                        Text(
                            text = months[selectedMonth - 1],
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 11.sp
                        )
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowDown,
                            contentDescription = "calendar",
                            modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.CenterEnd)
                        )
                    }
                }
            }
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NativeDatePicker(
        onDateSelected: ((Calendar) -> Unit)?,
        onDismiss: (() -> Unit)?,
    ) {
        val datePickerState = rememberDatePickerState()
        var showDialog by remember { mutableStateOf(false) }
        var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }

        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .width(150.dp)
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = CutCornerShape(3.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = DarkGreen
            ),
        ) {
            Text(text = formatDate(selectedDate))
        }

        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = {
                    onDismiss?.invoke()
                    showDialog = false
                },
                confirmButton = {
                    Button(onClick = {
                        val selectedDateMillis = datePickerState.selectedDateMillis
                        if (selectedDateMillis != null) {
                            val calendar = millisToCalendar(selectedDateMillis)
                            selectedDate = calendar
                            onDateSelected?.invoke(calendar)
                        }
                        showDialog = false
                    }) {
                        Text("Pilih")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        showDialog = false
                        onDismiss?.invoke()
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
