package com.muflidevs.foodapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.muflidevs.foodapp.R
import kotlin.math.ceil

object Converter {
    fun calculateYAxisSteps(profits: List<Long>, maxSteps: Int = 6): Pair<Int, Long> {
        val maxProfit = profits.maxOrNull() ?: 0

        val interval = ceil(maxProfit.toDouble() / maxSteps).toLong()

        return Pair(maxSteps, interval)
    }
}