package com.muflidevs.foodapp.ui.design_system

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle
import com.muflidevs.foodapp.data.remote.entity.Profit
import com.muflidevs.foodapp.ui.theme.orange
import com.muflidevs.foodapp.utils.Converter.calculateYAxisSteps
import com.muflidevs.foodapp.utils.Helper

@Composable
fun BarchartWithSolidBars() {
    val profits = listOf(
        Profit(1_000_000L),
        Profit(5_000_000L),
        Profit(6_000_000L),
        Profit(10_000_000L),
        Profit(8_000_000L),
        Profit(12_000_000L),
        Profit(90_000_000L),
        Profit(84_000_000L),
        Profit(50_000_000L),
        Profit(70_000_000L),
    )

    val (steps, interval) = calculateYAxisSteps(profits.map{ it.profits })
    val barData = listOf<BarData>(
        BarData(
            point = Point(0f, 10f),
            color = orange,
        ),
        BarData(
            point = Point(1f, 20f),
            color = orange,
        ),
        BarData(
            point = Point(2f, 40f),
            color = orange,
        ),
        BarData(
            point = Point(3f, 40f),
            color = orange,
        ),
        BarData(
            point = Point(4f, 40f),
            color = orange,
        ),
        BarData(
            point = Point(5f, 40f),
            color = orange,
        ),
        BarData(
            point = Point(6f, 40f),
            color = orange,
        ),
        BarData(
            point = Point(7f, 40f),
            color = orange,
        ),
        BarData(
            point = Point(8f, 3f),
            color = orange,
        ),
        BarData(
            point = Point(9f, 20f),
            color = orange,
        ),
        BarData(
            point = Point(10f, 40f),
            color = orange,
        ),
        BarData(
            point = Point(11f, 60f),
            color = orange,
        )

    )
    val yAxisLabels = (0..steps).map { it * interval }

    val xAxisData = AxisData.Builder()
        .axisStepSize(20.dp)
        .steps(barData.size - 1)
        .bottomPadding(50.dp)
        .axisLabelAngle(40f)
        .startDrawPadding(48.dp)
        .labelData { index ->
            Helper.getMonths().getOrNull(index) ?: ""
        }
        .build()
    val yAxisData = AxisData.Builder()
        .steps(yAxisLabels.size - 1)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> yAxisLabels.getOrNull(index).toString()}
        .build()
    val barChartData = BarChartData(
        chartData = barData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = BarStyle(
            paddingBetweenBars = 20.dp,
            barWidth = 25.dp
        ),
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 10.dp,
        backgroundColor = androidx.compose.ui.graphics.Color.Transparent
    )
    BarChart(
        modifier = Modifier
            .height(330.dp)
            .fillMaxWidth(),
        barChartData = barChartData
    )
}
