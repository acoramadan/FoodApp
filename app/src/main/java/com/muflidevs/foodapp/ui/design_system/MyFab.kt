package com.muflidevs.foodapp.ui.design_system

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FloatingActionButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FloatingActionButtonDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muflidevs.foodapp.utils.Helper


data class MiniFabItems(
    val icon: ImageVector,
    val title: String
)
@Composable
fun MyFab(isClicked: Boolean) {
    val items = listOf(
        MiniFabItems(Icons.Filled.Create, "Tambah Sayuran"),
        MiniFabItems(
            Icons.Filled.AddCircle,
            if (isClicked) "Tambah Pengeluaran" else "Tambah Pemasukan"
        )
    )
    var expanded by remember { mutableStateOf(false) }
    var isClickedPengeluaran by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(end = 8.dp, bottom = 72.dp)
    ) {
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 })
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.End
            ) {
                items.forEach { item ->
                    MiniFab(item.icon, item.title,isClicked)
                }
            }
        }

        FloatingActionButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.size(50.dp),
            backgroundColor = Helper.isPengeluaranClicked(isClicked),
            elevation = FloatingActionButtonDefaults.elevation(6.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Tambah",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun MiniFab(icon: ImageVector, title: String,isClicked: Boolean) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.padding(end = 5.dp, bottom = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(10.dp))

        ) {
            Text(text = title, fontSize = 12.sp, color = Color.Black)
        }
        Spacer(modifier = Modifier.width(10.dp))
        FloatingActionButton(
            onClick = {
                if (title != "Tambah Sayuran")
                    Toast.makeText(context, if (isClicked) "Tambah Pengeluaran" else "Tambah Pemasukan" ,Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(context,"Tombol tambah sayur", Toast.LENGTH_LONG).show()

            },
            modifier = Modifier.size(40.dp),
            backgroundColor = Helper.isPengeluaranClicked(isClicked),
            elevation = FloatingActionButtonDefaults.elevation(6.dp)
        ) {
            Icon(imageVector = icon, contentDescription = title, modifier = Modifier.size(20.dp))
        }
    }
}