package com.desh2403.bento_ds_compose.uikit.component.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

enum class ProgressBarType {
    CONTINUE, DISCRETE
}

enum class ProgressBarColor {
    INFORMATIVE, POSITIVE, WARNING, NEGATIVE, INTERACTIVE, SECONDARY
}

// TODO current value change animation
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
        ProgressBarColor.INTERACTIVE -> BentoDSTheme.colors.fg.interactive
        ProgressBarColor.SECONDARY -> BentoDSTheme.colors.fg.secondary
    }
    BoxWithConstraints(
        modifier = modifier.fillMaxWidth().height(BentoDSTheme.dims.x2),
        contentAlignment = Alignment.CenterStart,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = BentoDSTheme.colors.bg.overlay,
                    shape = BentoDSTheme.shapes.progressBarBgShape,
                )
        ){Text("")}
        Box(
            modifier = Modifier.width(
                ((currentValue / totalValue) * maxWidth.value).dp
            ).background(
                color = progressColor,
                shape = BentoDSTheme.shapes.progressBarFgShape,
            )
        ) { Text("")}
    }
}