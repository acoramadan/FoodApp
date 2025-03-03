package com.muflidevs.foodapp.ui.design_system

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource

@Composable
fun AuthOption(
    modifier: Modifier = Modifier,
    image: Int,
    tint: Color? = null,
    contentDescription: String? = null
) {
    Box (
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onBackground.copy(0.2f),
                shape = RoundedCornerShape(14.dp)
            )
            .clip(RoundedCornerShape(14.dp))
            .clickable {  }
            .padding(
                horizontal = 35.dp,
                vertical = 12.dp),
        contentAlignment = Alignment.Center
    ){
        if (tint != null) {
            Icon(
                painter = painterResource(image),
                contentDescription = contentDescription,
                modifier = Modifier.size(30.dp)
            )
        } else {
            Image (
                painter = painterResource(image),
                contentDescription = contentDescription,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}