package com.desh2403.bento_ds_compose.uikit.component.loading

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/** Kind of a [BentoDSProgressBar]: Bento DS `kind`. */
enum class ProgressBarType {
    /** Solid bar filled to `currentValue / totalValue`. Bento DS `continuous`. */
    CONTINUE,

    /** One segment per unit of `totalValue`, e.g. "3 of 5" onboarding steps. Bento DS `discrete`. */
    DISCRETE,
}

/** Fill color of a [BentoDSProgressBar]. */
enum class ProgressBarColor {
    INFORMATIVE, POSITIVE, WARNING, NEGATIVE, INTERACTIVE, SECONDARY
}

/**
 * Progress bar, continuous or split into segments. Value changes are animated.
 *
 * Bento DS: [ProgressBar](https://developer.bento-ds.com/Components/ProgressBar)
 *
 * Differences from Bento DS: no `label`; [color] is an extension.
 *
 * @param modifier modifier of the bar.
 * @param color fill color of the bar.
 * @param type continuous or discrete bar. Bento DS `kind`.
 * @param totalValue maximum value. Bento DS `maxValue`.
 * @param currentValue current value. Bento DS `value`.
 */
@Composable
fun BentoDSProgressBar(
    modifier: Modifier = Modifier,
    color: ProgressBarColor = ProgressBarColor.INTERACTIVE,
    type: ProgressBarType,
    totalValue: Double,
    currentValue: Double,
) {
    val progressColor = when (color) {
        ProgressBarColor.INFORMATIVE -> BentoDSTheme.colors.fg.informative
        ProgressBarColor.POSITIVE -> BentoDSTheme.colors.fg.positive
        ProgressBarColor.WARNING -> BentoDSTheme.colors.fg.warning
        ProgressBarColor.NEGATIVE -> BentoDSTheme.colors.fg.negative
        ProgressBarColor.INTERACTIVE -> BentoDSTheme.colors.primary.solidEnabledBg
        ProgressBarColor.SECONDARY -> BentoDSTheme.colors.fg.secondary
    }
    val fraction = if (totalValue > 0) (currentValue / totalValue).coerceIn(0.0, 1.0) else 0.0
    val barModifier = modifier
        .fillMaxWidth()
        .height(BentoDSTheme.dims.x2)
        .semantics {
            progressBarRangeInfo = ProgressBarRangeInfo(
                current = currentValue.toFloat(),
                range = 0f..totalValue.toFloat().coerceAtLeast(0f),
            )
        }
    when (type) {
        ProgressBarType.CONTINUE -> ContinuousProgressBar(
            modifier = barModifier,
            fraction = fraction.toFloat(),
            progressColor = progressColor,
        )

        ProgressBarType.DISCRETE -> DiscreteProgressBar(
            modifier = barModifier,
            segments = totalValue.toInt(),
            filled = currentValue.toInt(),
            progressColor = progressColor,
        )
    }
}

@Composable
private fun ContinuousProgressBar(
    modifier: Modifier,
    fraction: Float,
    progressColor: Color,
) {
    val animatedFraction by animateFloatAsState(
        targetValue = fraction,
        animationSpec = tween(
            durationMillis = BentoDSTheme.motion.durationMedium,
            easing = BentoDSTheme.motion.easingStandard,
        ),
        label = "progressFraction",
    )
    Box(
        modifier = modifier.background(
            color = BentoDSTheme.colors.bg.sunken,
            shape = BentoDSTheme.shapes.progressBarBgShape,
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(animatedFraction)
                .fillMaxHeight()
                .background(
                    color = progressColor,
                    shape = BentoDSTheme.shapes.progressBarFgShape,
                )
        )
    }
}

@Composable
private fun DiscreteProgressBar(
    modifier: Modifier,
    segments: Int,
    filled: Int,
    progressColor: Color,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(BentoDSTheme.dims.x1),
    ) {
        repeat(segments) { index ->
            val segmentColor by animateColorAsState(
                targetValue = if (index < filled) progressColor
                else BentoDSTheme.colors.bg.sunken,
                animationSpec = tween(
                    durationMillis = BentoDSTheme.motion.durationMedium,
                    easing = BentoDSTheme.motion.easingStandard,
                ),
                label = "progressSegment",
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(
                        color = segmentColor,
                        shape = BentoDSTheme.shapes.progressBarFgShape,
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BentoDSProgressBarPreview() {
    BentoDSTheme(darkMode = false) {
        Column(
            modifier = Modifier.padding(BentoDSTheme.dims.x4),
            verticalArrangement = Arrangement.spacedBy(BentoDSTheme.dims.x4),
        ) {
            BentoDSProgressBar(
                type = ProgressBarType.CONTINUE,
                totalValue = 5.0,
                currentValue = 3.0,
            )
            BentoDSProgressBar(
                type = ProgressBarType.DISCRETE,
                totalValue = 5.0,
                currentValue = 3.0,
            )
        }
    }
}
