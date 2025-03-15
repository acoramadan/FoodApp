package com.muflidevs.foodapp.ui.design_system

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FloatingActionButton
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.muflidevs.foodapp.ui.theme.DarkGreen
import com.muflidevs.foodapp.utils.Helper


data class MiniFabItems(
    val icon: ImageVector,
    val title: String
)
@Composable
fun MyFab(isCliked: Boolean) {
    val items = listOf(
        MiniFabItems(Icons.Filled.Create, "Tambah sayuran"),
        MiniFabItems(Icons.Filled.AddCircle, if(isCliked) "Tambah Pengeluaran" else "Tambah Pemasukan")
    )
    var expanded by remember { mutableStateOf(false) }
    Column (
        horizontalAlignment = Alignment.End
    ) {
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + slideInVertically(initialOffsetY = {it}) + expandVertically(),
            exit = fadeOut() + slideOutVertically(targetOffsetY = {it}) + shrinkVertically()
        ) {
            LazyColumn (
                Modifier.padding(bottom = 8.dp)
            ) {
                items(items.size) {
                    ItemUi(icon = items[it].icon, title = items[it].title)
                }
            }
        }

        val transition = updateTransition(
            targetState = expanded,
            label = "transition"
        )

        val rotation by transition.animateFloat(label = "rotation") {
            if (it) 315f else 0f
        }
        FloatingActionButton(
            onClick = {expanded = !expanded},
            modifier = Modifier.rotate(rotation),
            contentColor = Helper.changeButtonColorPemasukan(expanded)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "icon add"
            )
        }
    }
}

@Composable
fun ItemUi(icon: ImageVector, title: String) {
    val context = LocalContext.current
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
        Spacer(modifier = Modifier.weight(1f))
        Box(modifier = Modifier
            .border(2.dp, color = DarkGreen, RoundedCornerShape(10.dp))) {
            Text(text = title)
        }
        Spacer(modifier = Modifier.width(10.dp))
        FloatingActionButton(
            onClick = {},
            contentColor = DarkGreen,
            modifier = Modifier.size(45.dp)
        ) {
            Icon(imageVector = icon, contentDescription = "")
        }
    }
}