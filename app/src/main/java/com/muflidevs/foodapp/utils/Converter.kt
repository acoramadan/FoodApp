package com.muflidevs.foodapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.muflidevs.foodapp.R

object Converter {
    @Composable
    fun convertImageToVector(image: Int) : ImageVector = ImageVector.vectorResource(id = image)
}