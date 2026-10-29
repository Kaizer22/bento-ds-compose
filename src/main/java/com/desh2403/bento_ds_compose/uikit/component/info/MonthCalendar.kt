package com.desh2403.bento_ds_compose.uikit.component.info

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSIconButton
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonType
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

/**
 * Style of a day in [BentoDSMonthCalendar]:
 * - [FILLED]: brand fill, the main state (e.g. "done");
 * - [TINTED]: light brand fill, an intermediate state;
 * - [OUTLINED]: border without fill, a special state (e.g. "skipped");
 * - [PLAIN]: a regular day;
 * - [MUTED]: a secondary day;
 * - [DISABLED]: an unavailable day that cannot be clicked.
 */
enum class CalendarDayStyle {
    FILLED, TINTED, OUTLINED, PLAIN, MUTED, DISABLED,
}

/**
 * Appearance of a day in [BentoDSMonthCalendar].
 *
 * @param style fill and text color of the day.
 * @param isHighlighted draws a focus border, e.g. for today.
 * @param hasMarker draws a dot under a bold number, e.g. when the day has an entry.
 * @param contentDescription accessibility label of the day instead of its number.
 */
data class BentoDSCalendarDay(
    val style: CalendarDayStyle = CalendarDayStyle.PLAIN,
    val isHighlighted: Boolean = false,
    val hasMarker: Boolean = false,
    val contentDescription: String? = null,
)

/**
 * Flat month calendar: a header with month navigation, a row of weekday names and a grid of days
 * starting on Monday. Each day can be styled separately, so the calendar works both as a date picker
 * and as a habit or mood heatmap.
 *
 * Bento DS has no calendar component; the closest one is the date picker of
 * [DateField](https://developer.bento-ds.com/Components/DateField). The whole component is an extension
 * of this library.
 *
 * @param month shown month.
 * @param onMonthChange called with the previous or the next month when a navigation button is clicked.
 * @param modifier modifier of the calendar.
 * @param title header text; the month and year in the device locale by default.
 * @param subtitle line under the header, e.g. a month summary.
 * @param canGoPrevious enables the previous month button.
 * @param canGoNext enables the next month button.
 * @param weekDayProvider weekday names; short standalone names in the device locale by default.
 * @param dayProvider appearance of each day of the month.
 * @param onDayClick called with the clicked day; `null` makes the days non-interactive.
 * @param previousMonthLabel accessibility label of the previous month button.
 * @param nextMonthLabel accessibility label of the next month button.
 */
@Composable
fun BentoDSMonthCalendar(
    month: YearMonth,
    onMonthChange: (YearMonth) -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    subtitle: String? = null,
    canGoPrevious: Boolean = true,
    canGoNext: Boolean = true,
    weekDayProvider: ((DayOfWeek) -> String)? = null,
    dayProvider: (LocalDate) -> BentoDSCalendarDay = { BentoDSCalendarDay() },
    onDayClick: ((LocalDate) -> Unit)? = null,
    previousMonthLabel: String? = null,
    nextMonthLabel: String? = null,
) {
    val locale = LocalConfiguration.current.locales[0]
    val gap = BentoDSTheme.dims.x1
    Column(modifier = modifier) {
        CalendarHeader(
            title = title ?: defaultTitle(month, locale),
            subtitle = subtitle,
            canGoPrevious = canGoPrevious,
            canGoNext = canGoNext,
            onPrevious = { onMonthChange(month.minusMonths(1)) },
            onNext = { onMonthChange(month.plusMonths(1)) },
            previousLabel = previousMonthLabel,
            nextLabel = nextMonthLabel,
        )
        VSpace(BentoDSTheme.dims.x2)
        Row(horizontalArrangement = Arrangement.spacedBy(gap)) {
            DayOfWeek.entries.forEach { dayOfWeek ->
                Text(
                    modifier = Modifier.weight(1f),
                    text = weekDayProvider?.invoke(dayOfWeek)
                        ?: dayOfWeek.getDisplayName(TextStyle.SHORT_STANDALONE, locale),
                    style = BentoDSTheme.typography.bodySmall,
                    color = BentoDSTheme.colors.text.secondary,
                    textAlign = TextAlign.Center,
                )
            }
        }
        VSpace(BentoDSTheme.dims.x2)
        val leadingBlanks = month.atDay(1).dayOfWeek.value - 1
        val cells = List(leadingBlanks) { null } + (1..month.lengthOfMonth()).map { month.atDay(it) }
        Column(verticalArrangement = Arrangement.spacedBy(gap)) {
            cells.chunked(DayOfWeek.entries.size).forEach { week ->
                Row(horizontalArrangement = Arrangement.spacedBy(gap)) {
                    week.forEach { date ->
                        if (date == null) {
                            Spacer(Modifier.weight(1f))
                        } else {
                            CalendarDayCell(
                                modifier = Modifier.weight(1f),
                                date = date,
                                day = dayProvider(date),
                                onClick = onDayClick?.let { { it(date) } },
                            )
                        }
                    }
                    repeat(DayOfWeek.entries.size - week.size) { Spacer(Modifier.weight(1f)) }
                }
            }
        }
    }
}

@Composable
private fun CalendarHeader(
    title: String,
    subtitle: String?,
    canGoPrevious: Boolean,
    canGoNext: Boolean,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    previousLabel: String?,
    nextLabel: String?,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = BentoDSTheme.typography.titleMedium,
                color = BentoDSTheme.colors.text.primary,
            )
            subtitle?.let {
                VSpace(BentoDSTheme.dims.x0_5)
                Text(
                    text = it,
                    style = BentoDSTheme.typography.bodySmall,
                    color = BentoDSTheme.colors.text.secondary,
                )
            }
        }
        BentoDSIconButton(
            iconRes = R.drawable.ic_chevron_left,
            onClick = onPrevious,
            enabled = canGoPrevious,
            size = IconSize.M,
            buttonType = ButtonType.SECONDARY_TRANSPARENT,
            iconTint = if (canGoPrevious) BentoDSTheme.colors.fg.primary else BentoDSTheme.colors.fg.disabled,
            contentDescription = previousLabel,
        )
        BentoDSIconButton(
            iconRes = R.drawable.ic_chevron_right,
            onClick = onNext,
            enabled = canGoNext,
            size = IconSize.M,
            buttonType = ButtonType.SECONDARY_TRANSPARENT,
            iconTint = if (canGoNext) BentoDSTheme.colors.fg.primary else BentoDSTheme.colors.fg.disabled,
            contentDescription = nextLabel,
        )
    }
}

@Composable
private fun CalendarDayCell(
    date: LocalDate,
    day: BentoDSCalendarDay,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    val colors = BentoDSTheme.colors
    val shape = BentoDSTheme.shapes.tileShape
    val background = when (day.style) {
        CalendarDayStyle.FILLED -> colors.bg.interactive
        CalendarDayStyle.TINTED -> colors.brand.tertiary
        else -> Color.Transparent
    }
    val textColor = when (day.style) {
        CalendarDayStyle.FILLED -> colors.fg.onInteractive
        CalendarDayStyle.TINTED -> colors.text.interactive
        CalendarDayStyle.PLAIN -> colors.text.primary
        CalendarDayStyle.OUTLINED, CalendarDayStyle.MUTED -> colors.text.secondary
        CalendarDayStyle.DISABLED -> colors.text.disabled
    }
    val border = when {
        day.isHighlighted -> Modifier.border(
            BentoDSTheme.dims.x0_25 * HIGHLIGHT_WIDTH_FACTOR,
            colors.outline.inputFocus,
            shape,
        )
        day.style == CalendarDayStyle.OUTLINED -> Modifier.border(
            BentoDSTheme.dims.x0_25,
            colors.outline.container,
            shape,
        )
        else -> Modifier
    }
    val isClickable = onClick != null && day.style != CalendarDayStyle.DISABLED
    Box(
        modifier = modifier
            .height(BentoDSTheme.dims.x10)
            .clip(shape)
            .background(background)
            .then(border)
            .then(
                if (isClickable) Modifier.clickable(role = Role.Button, onClick = onClick!!)
                else Modifier
            )
            .then(
                if (day.contentDescription != null) {
                    Modifier.semantics { contentDescription = day.contentDescription }
                } else Modifier
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            style = if (day.hasMarker) BentoDSTheme.typography.bodyMediumStrong
            else BentoDSTheme.typography.labelMedium,
            color = textColor,
        )
        if (day.hasMarker) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = BentoDSTheme.dims.x1)
                    .size(BentoDSTheme.dims.x1 + BentoDSTheme.dims.x0_25)
                    .background(
                        color = if (day.style == CalendarDayStyle.FILLED) colors.fg.onInteractive
                        else colors.bg.interactive,
                        shape = CircleShape,
                    ),
            )
        }
    }
}

private const val HIGHLIGHT_WIDTH_FACTOR = 1.5f

private fun defaultTitle(month: YearMonth, locale: Locale): String =
    month.format(DateTimeFormatter.ofPattern("LLLL yyyy", locale))
        .replaceFirstChar { it.titlecase(locale) }

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun MonthCalendarPreview() {
    var month by remember { mutableStateOf(YearMonth.of(2026, 10)) }
    val today = LocalDate.of(2026, 10, 15)
    BentoDSTheme {
        BentoDSMonthCalendar(
            modifier = Modifier.fillMaxWidth(),
            month = month,
            onMonthChange = { month = it },
            dayProvider = { date ->
                BentoDSCalendarDay(
                    style = when {
                        date > today -> CalendarDayStyle.DISABLED
                        date.dayOfMonth == 10 -> CalendarDayStyle.OUTLINED
                        date.dayOfMonth % 5 == 1 -> CalendarDayStyle.MUTED
                        else -> CalendarDayStyle.FILLED
                    },
                    isHighlighted = date == today,
                )
            },
            onDayClick = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun MonthCalendarMarkersPreview() {
    val today = LocalDate.of(2026, 10, 15)
    val selected = LocalDate.of(2026, 10, 13)
    val withMarker = setOf(1, 3, 4, 6, 8, 9, 10, 12, 13, 14, 15)
    BentoDSTheme {
        BentoDSMonthCalendar(
            modifier = Modifier.fillMaxWidth(),
            month = YearMonth.of(2026, 10),
            onMonthChange = {},
            subtitle = "11 entries in 11 of 15 days",
            canGoNext = false,
            dayProvider = { date ->
                val hasMarker = date.dayOfMonth in withMarker
                BentoDSCalendarDay(
                    style = when {
                        date > today -> CalendarDayStyle.DISABLED
                        date == selected -> CalendarDayStyle.TINTED
                        hasMarker -> CalendarDayStyle.PLAIN
                        else -> CalendarDayStyle.MUTED
                    },
                    isHighlighted = date == today || date == selected,
                    hasMarker = hasMarker,
                )
            },
            onDayClick = {},
        )
    }
}
