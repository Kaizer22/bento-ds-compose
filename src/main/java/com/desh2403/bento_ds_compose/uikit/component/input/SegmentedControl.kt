package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/**
 * Switch between 2–4 mutually exclusive options on a shared track.
 *
 * Bento DS has no segmented control; the closest one is
 * [Tabs](https://developer.bento-ds.com/Components/Tabs). The whole component is an extension of this library.
 *
 * @param options labels of the options.
 * @param selectedIndex index of the selected option.
 * @param onSelect called with the index of the clicked option.
 * @param modifier modifier of the control.
 * @param fillWidth the segments take the full width: each one gets the width of its label and the rest
 * is split evenly, so a long label is not truncated next to short ones.
 */
@Composable
fun BentoDSSegmentedControl(
    options: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    fillWidth: Boolean = false,
) {
    val gap = BentoDSTheme.dims.x0_5
    Layout(
        modifier = modifier
            .clip(BentoDSTheme.shapes.inputShape)
            .background(BentoDSTheme.colors.bg.secondary)
            .padding(BentoDSTheme.dims.x1)
            .selectableGroup(),
        content = {
            options.forEachIndexed { index, option ->
                Segment(
                    text = option,
                    selected = index == selectedIndex,
                    onClick = { onSelect(index) },
                )
            }
        },
    ) { measurables, constraints ->
        if (measurables.isEmpty()) return@Layout layout(constraints.minWidth, 0) {}
        val gapPx = gap.roundToPx()
        val height = measurables.maxOfOrNull { it.minIntrinsicHeight(Constraints.Infinity) } ?: 0
        val natural = measurables.map { it.maxIntrinsicWidth(height) }
        val gaps = gapPx * (measurables.size - 1).coerceAtLeast(0)
        val widths = if (fillWidth && constraints.hasBoundedWidth) {
            val available = constraints.maxWidth - gaps
            val extra = available - natural.sum()
            if (extra >= 0) {
                natural.map { it + extra / natural.size }
            } else {
                natural.map { it * available / natural.sum() }
            }
        } else natural
        val placeables = measurables.mapIndexed { index, measurable ->
            measurable.measure(Constraints.fixed(widths[index], height))
        }
        val width = (placeables.sumOf { it.width } + gaps).coerceIn(constraints.minWidth, constraints.maxWidth)
        layout(width, height) {
            var x = 0
            placeables.forEach {
                it.placeRelative(x, 0)
                x += it.width + gapPx
            }
        }
    }
}

@Composable
private fun Segment(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val bg by animateColorAsState(
        targetValue = if (selected) BentoDSTheme.colors.bg.elevated
        else BentoDSTheme.colors.bg.secondary,
        animationSpec = tween(BentoDSTheme.motion.durationShort),
        label = "segment bg",
    )
    Box(
        modifier = Modifier
            .height(BentoDSTheme.dims.x8)
            .clip(BentoDSTheme.shapes.buttonShape)
            .background(bg)
            .selectable(
                selected = selected,
                role = Role.Tab,
                onClick = onClick,
            )
            .padding(horizontal = BentoDSTheme.dims.x3),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = BentoDSTheme.typography.labelMedium,
            color = if (selected) BentoDSTheme.colors.text.primary
            else BentoDSTheme.colors.text.secondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview
@Composable
private fun BentoDSSegmentedControlPreview() {
    var selected by remember { mutableIntStateOf(0) }
    BentoDSTheme {
        Column {
            BentoDSSegmentedControl(
                options = listOf("Outline", "Fill"),
                selectedIndex = selected,
                onSelect = { selected = it },
            )
            BentoDSSegmentedControl(
                modifier = Modifier.width(328.dp),
                options = listOf("System default", "Light", "Dark"),
                selectedIndex = 0,
                onSelect = {},
                fillWidth = true,
            )
        }
    }
}
