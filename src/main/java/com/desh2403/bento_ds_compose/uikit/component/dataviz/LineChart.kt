package com.desh2403.bento_ds_compose.uikit.component.dataviz

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/**
 * Line of a [BentoDSLineChart].
 *
 * @param values values in the order of the X axis; `null` means no data and breaks the line.
 * @param color color of the line.
 * @param label name of the line in the legend.
 */
@Immutable
data class BentoDSLineSeries(
    val values: List<Float?>,
    val color: Color,
    val label: String? = null,
)

/**
 * Line chart with a horizontal grid, axis labels and a legend. Everything except the lines can be
 * turned off to get a calm sparkline.
 *
 * Bento DS: [LineChart](https://developer.bento-ds.com/Components/LineChart)
 *
 * Differences from Bento DS: no tooltip or animation; the data is a list of [BentoDSLineSeries]
 * instead of records with `categories`. [gridValues], [xUnit], [yUnit], [plotHeight], [strokeWidth]
 * and [labelStyle] are extensions.
 *
 * @param series lines of the chart. Bento DS `data`, `categories` and `dataColors`.
 * @param valueRange values at the bottom and the top of the plot area.
 * @param modifier modifier of the chart.
 * @param gridValues values of the horizontal grid lines.
 * @param xLabels labels under the X axis: point index to text. The outermost labels stick to the edges.
 * Bento DS `hideXAxis` and `xAxisValueFormatter`.
 * @param yLabel label of a grid line value; `null` hides the labels. Bento DS `hideYAxis` and `yAxisValueFormatter`.
 * @param xUnit caption under the X axis.
 * @param yUnit caption above the Y axis.
 * @param showAxes draws the Y axis and ticks under the X labels.
 * @param smooth draws smooth curves; `false` draws straight segments. Bento DS `lineType`.
 * @param plotHeight height of the plot area without the labels.
 * @param strokeWidth width of the lines.
 * @param labelStyle text style of the axis labels.
 * @param contentDescription accessibility description of the chart.
 */
@Composable
fun BentoDSLineChart(
    series: List<BentoDSLineSeries>,
    valueRange: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier,
    gridValues: List<Float> = listOf(
        valueRange.start,
        (valueRange.start + valueRange.endInclusive) / 2,
        valueRange.endInclusive,
    ),
    xLabels: Map<Int, String> = emptyMap(),
    yLabel: ((Float) -> String)? = null,
    xUnit: String? = null,
    yUnit: String? = null,
    showAxes: Boolean = true,
    smooth: Boolean = true,
    plotHeight: Dp = BentoDSTheme.dims.x40,
    strokeWidth: Dp = BentoDSTheme.dims.x0_5,
    labelStyle: TextStyle = BentoDSTheme.typography.labelSmall,
    contentDescription: String? = null,
) {
    val colors = BentoDSTheme.colors
    val dims = BentoDSTheme.dims
    val textMeasurer = rememberTextMeasurer()
    val axisLabelStyle = labelStyle.copy(color = colors.text.secondary)
    val gridColor = colors.outline.decorative
    val axisColor = colors.text.secondary
    val pointsCount = series.maxOfOrNull { it.values.size } ?: 0
    val labelGap = dims.x2
    val tickLength = dims.x1
    val xLabelsHeight = if (xLabels.isEmpty()) dims.x0 else {
        with(LocalDensity.current) {
            textMeasurer.measure("0", axisLabelStyle).size.height.toDp()
        } + labelGap
    }

    Column(
        modifier = modifier.then(
            if (contentDescription != null) Modifier.semantics { this.contentDescription = contentDescription }
            else Modifier
        ),
    ) {
        yUnit?.let {
            Text(text = it, style = axisLabelStyle)
            VSpace(dims.x1)
        }
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(plotHeight + xLabelsHeight),
        ) {
            val stroke = strokeWidth.toPx()
            val yLabelLayouts = yLabel?.let { format ->
                gridValues.associateWith { textMeasurer.measure(format(it), axisLabelStyle) }
            }.orEmpty()
            val yLabelsWidth = yLabelLayouts.values.maxOfOrNull { it.size.width }?.toFloat() ?: 0f
            val left = if (yLabelsWidth > 0f) yLabelsWidth + labelGap.toPx() else 0f
            // Half a stroke of space at the top and the bottom keeps the extreme values from being clipped
            val top = stroke / 2
            val bottom = plotHeight.toPx() - stroke / 2
            val width = size.width - left
            val span = (valueRange.endInclusive - valueRange.start).takeIf { it != 0f } ?: 1f
            fun yOf(value: Float): Float =
                bottom - (value - valueRange.start) / span * (bottom - top)
            fun xOf(index: Int): Float =
                if (pointsCount <= 1) left + width / 2 else left + index * width / (pointsCount - 1)

            gridValues.forEach { value ->
                val y = yOf(value)
                drawLine(gridColor, Offset(left, y), Offset(size.width, y), dims.x0_25.toPx())
                yLabelLayouts[value]?.let { layout ->
                    drawText(layout, topLeft = Offset(yLabelsWidth - layout.size.width, y - layout.size.height / 2))
                }
            }
            if (showAxes) {
                drawLine(axisColor, Offset(left, top), Offset(left, bottom), dims.x0_25.toPx())
            }

            series.forEach { line -> drawSeries(line, ::xOf, ::yOf, stroke, smooth) }

            xLabels.forEach { (index, text) ->
                val layout = textMeasurer.measure(text, axisLabelStyle)
                val x = xOf(index)
                if (showAxes) {
                    drawLine(axisColor, Offset(x, bottom), Offset(x, bottom + tickLength.toPx()), dims.x0_25.toPx())
                }
                val labelX = (x - layout.size.width / 2f).coerceIn(left, size.width - layout.size.width)
                drawText(layout, topLeft = Offset(labelX, plotHeight.toPx() + labelGap.toPx()))
            }
        }
        xUnit?.let {
            VSpace(dims.x1)
            Text(modifier = Modifier.align(Alignment.End), text = it, style = axisLabelStyle)
        }
        val legend = series.filter { it.label != null }
        if (legend.size > 1) {
            VSpace(dims.x2)
            LineChartLegend(series = legend, style = axisLabelStyle)
        }
    }
}

private fun DrawScope.drawSeries(
    line: BentoDSLineSeries,
    xOf: (Int) -> Float,
    yOf: (Float) -> Float,
    stroke: Float,
    smooth: Boolean,
) {
    val runs = mutableListOf<List<Offset>>()
    var run = mutableListOf<Offset>()
    line.values.forEachIndexed { index, value ->
        if (value == null) {
            if (run.isNotEmpty()) runs += run
            run = mutableListOf()
        } else {
            run += Offset(xOf(index), yOf(value))
        }
    }
    if (run.isNotEmpty()) runs += run

    runs.forEach { points ->
        if (points.size == 1) {
            drawCircle(line.color, radius = stroke, center = points.first())
            return@forEach
        }
        val path = Path().apply {
            moveTo(points.first().x, points.first().y)
            for (i in 1 until points.size) {
                val from = points[i - 1]
                val to = points[i]
                if (smooth) {
                    val midX = (from.x + to.x) / 2
                    cubicTo(midX, from.y, midX, to.y, to.x, to.y)
                } else {
                    lineTo(to.x, to.y)
                }
            }
        }
        drawPath(path, line.color, style = Stroke(width = stroke, cap = StrokeCap.Round, join = StrokeJoin.Round))
    }
}

@Composable
private fun LineChartLegend(
    series: List<BentoDSLineSeries>,
    style: TextStyle,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BentoDSTheme.dims.x6, Alignment.CenterHorizontally),
    ) {
        series.forEach { line ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(width = BentoDSTheme.dims.x4, height = BentoDSTheme.dims.x0_5 + BentoDSTheme.dims.x0_25)
                        .background(line.color),
                )
                HSpace(BentoDSTheme.dims.x2)
                Text(text = line.label.orEmpty(), style = style)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun LineChartPreview() {
    BentoDSTheme {
        BentoDSLineChart(
            series = listOf(
                BentoDSLineSeries(listOf(18f, 20f, 13f, 19f, 16f, 19f, 13f), BentoDSTheme.colors.fg.informative, "Category 1"),
                BentoDSLineSeries(listOf(12f, 14f, 11f, 12f, 9f, 12f, 17f), BentoDSTheme.colors.fg.positive, "Category 2"),
                BentoDSLineSeries(listOf(9f, 5f, 12f, null, 8f, 7f, 6f), BentoDSTheme.colors.fg.negative, "Category 3"),
            ),
            valueRange = 0f..25f,
            gridValues = listOf(0f, 5f, 10f, 15f, 20f, 25f),
            xLabels = mapOf(1 to "A", 3 to "B", 5 to "C"),
            yLabel = { it.toInt().toString() },
            xUnit = "X UNIT",
            yUnit = "Y UNIT",
        )
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun LineChartSparklinePreview() {
    BentoDSTheme {
        BentoDSLineChart(
            series = listOf(
                BentoDSLineSeries(
                    values = listOf(3.8f, 4.1f, 4f, 4.4f, 4.2f, 3.6f, 2.7f, 2.2f, 2.6f, null, 4.1f, 4.4f, 4.7f, 4.6f, 5f, 4.8f),
                    color = BentoDSTheme.colors.fg.interactive,
                ),
            ),
            valueRange = 1f..5f,
            xLabels = mapOf(0 to "1", 5 to "10", 10 to "20", 15 to "30"),
            showAxes = false,
            smooth = false,
            plotHeight = BentoDSTheme.dims.x18,
            labelStyle = BentoDSTheme.typography.bodySmall,
        )
    }
}
