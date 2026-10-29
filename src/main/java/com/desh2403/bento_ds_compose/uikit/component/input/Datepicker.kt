package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSButton
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonSize
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonType
import com.desh2403.bento_ds_compose.uikit.component.info.BentoDSCalendarDay
import com.desh2403.bento_ds_compose.uikit.component.info.BentoDSCard
import com.desh2403.bento_ds_compose.uikit.component.info.BentoDSMonthCalendar
import com.desh2403.bento_ds_compose.uikit.component.info.CalendarDayStyle
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

/**
 * Date picker on a card: a month calendar with the selected day filled and today outlined.
 *
 * Bento DS: the picker of [DateField](https://developer.bento-ds.com/Components/DateField).
 *
 * Differences from Bento DS: the picker is shown inline instead of in a popup; see
 * [com.desh2403.bento_ds_compose.uikit.component.input.field.BentoDSDateField] for the field with a popup.
 * Range selection is not supported. [showPresetsButtons] is an extension.
 *
 * @param year year of the selected date.
 * @param month month of the selected date, 0-based: 0 is January.
 * @param dayOfMonth day of the selected date, 1-based.
 * @param onSelectDay called with the clicked day; the month is 0-based.
 * @param weekDayProvider weekday names by index, 0 is Monday; short names in the device locale by default.
 * @param showPresetsButtons shows "This month" and "This year" buttons that jump to the current month.
 * @param thisMonthLabel label of the "This month" button.
 * @param thisYearLabel label of the "This year" button.
 */
@Composable
fun BentoDSDatepicker(
    year: Int,
    month: Int,
    dayOfMonth: Int,
    onSelectDay: (dayOfMonth: Int, month: Int, year: Int) -> Unit,
    weekDayProvider: ((dayOfWeek: Int) -> String)? = null,
    showPresetsButtons: Boolean = false,
    thisMonthLabel: String = "This month",
    thisYearLabel: String = "This year",
) {
    val selected = LocalDate.of(year, month + 1, dayOfMonth)
    var shownMonth by remember { mutableStateOf(YearMonth.from(selected)) }
    val today = LocalDate.now()
    BentoDSCard {
        Column {
            BentoDSMonthCalendar(
                modifier = Modifier.fillMaxWidth(),
                month = shownMonth,
                onMonthChange = { shownMonth = it },
                weekDayProvider = weekDayProvider?.let { provider ->
                    { dayOfWeek: DayOfWeek -> provider(dayOfWeek.value - 1) }
                },
                dayProvider = { date ->
                    BentoDSCalendarDay(
                        style = if (date == selected) CalendarDayStyle.FILLED else CalendarDayStyle.PLAIN,
                        isHighlighted = date == today && date != selected,
                    )
                },
                onDayClick = { date -> onSelectDay(date.dayOfMonth, date.monthValue - 1, date.year) },
            )
            if (showPresetsButtons) {
                Row {
                    BentoDSButton(
                        text = thisMonthLabel,
                        buttonType = ButtonType.SECONDARY_TRANSPARENT,
                        buttonSize = ButtonSize.S,
                        onClick = { shownMonth = YearMonth.now() },
                    )
                    BentoDSButton(
                        text = thisYearLabel,
                        buttonType = ButtonType.SECONDARY_TRANSPARENT,
                        buttonSize = ButtonSize.S,
                        onClick = { shownMonth = shownMonth.withYear(YearMonth.now().year) },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DatepickerPreview() {
    BentoDSTheme {
        var date by remember { mutableStateOf(LocalDate.of(2025, 7, 14)) }
        BentoDSDatepicker(
            year = date.year,
            month = date.monthValue - 1,
            dayOfMonth = date.dayOfMonth,
            onSelectDay = { day, month, year -> date = LocalDate.of(year, month + 1, day) },
            showPresetsButtons = true,
        )
    }
}
