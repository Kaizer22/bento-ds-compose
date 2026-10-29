package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

/**
 * Weekday picker: seven round toggles across the full width, each switched on separately.
 *
 * Bento DS has no weekday picker. The whole component is an extension of this library.
 *
 * @param selectedDays selected days.
 * @param onDayClick called with the clicked day; the caller updates [selectedDays].
 * @param modifier modifier of the picker.
 * @param firstDayOfWeek day that starts the row.
 * @param enabled whether the toggles react to clicks.
 */
@Composable
fun BentoDSWeekDaysSelector(
    selectedDays: Set<DayOfWeek>,
    onDayClick: (DayOfWeek) -> Unit,
    modifier: Modifier = Modifier,
    firstDayOfWeek: DayOfWeek = DayOfWeek.MONDAY,
    enabled: Boolean = true,
) {
    val locale = LocalConfiguration.current.locales[0]
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(BentoDSTheme.dims.x1 + BentoDSTheme.dims.x0_5),
    ) {
        (0L until DayOfWeek.entries.size).map { firstDayOfWeek.plus(it) }.forEach { day ->
            WeekDay(
                modifier = Modifier.weight(1f),
                label = day.shortLabel(locale),
                selected = day in selectedDays,
                enabled = enabled,
                onClick = { onDayClick(day) },
            )
        }
    }
}

@Composable
private fun WeekDay(
    label: String,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val bg by animateColorAsState(
        targetValue = if (selected) BentoDSTheme.colors.bg.interactive
        else BentoDSTheme.colors.bg.secondary,
        animationSpec = tween(BentoDSTheme.motion.durationShort),
        label = "week day bg",
    )
    Box(
        modifier = modifier
            .height(BentoDSTheme.dims.x10 + BentoDSTheme.dims.x1)
            .clip(BentoDSTheme.shapes.roundShape)
            .background(bg)
            .toggleable(
                value = selected,
                enabled = enabled,
                role = Role.Checkbox,
                onValueChange = { onClick() },
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label,
            style = BentoDSTheme.typography.labelMedium,
            color = when {
                !enabled -> BentoDSTheme.colors.text.disabled
                selected -> BentoDSTheme.colors.fg.onInteractive
                else -> BentoDSTheme.colors.text.primary
            },
            maxLines = 1,
        )
    }
}

private fun DayOfWeek.shortLabel(locale: Locale): String =
    getDisplayName(TextStyle.SHORT_STANDALONE, locale)
        .replaceFirstChar { it.titlecase(locale) }

@Preview
@Composable
private fun BentoDSWeekDaysSelectorPreview() {
    var selected by remember {
        mutableStateOf(setOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY))
    }
    BentoDSTheme {
        Column {
            BentoDSWeekDaysSelector(
                modifier = Modifier.width(328.dp),
                selectedDays = selected,
                onDayClick = { day ->
                    selected = if (day in selected) selected - day else selected + day
                },
            )
        }
    }
}
