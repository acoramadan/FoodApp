package com.muflidevs.foodapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding


import androidx.compose.material.Icon

import androidx.compose.material.IconButton

import androidx.compose.material.Scaffold

import androidx.compose.material.Text

import androidx.compose.material.TextField

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muflidevs.foodapp.utils.Custom
import com.muflidevs.foodapp.utils.Page
import java.util.Calendar

@Composable
fun BerandaScreen(modifier: Modifier = Modifier) {
    var selectedYear by remember { mutableIntStateOf(Calendar.getInstance().get(Calendar.YEAR)) }
    var showDatePicker by remember { mutableStateOf(false) }

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Halaman Beranda")
            }
        }
    )

    if (showDatePicker) {
        Custom.DatePicker(
            page = Page.YEAR_ONLY,
            onDateSelected = { calendar ->
                selectedYear = calendar.get(Calendar.YEAR)
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false }
        )
    }
}