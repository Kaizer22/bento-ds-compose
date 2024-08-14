package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSButton
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSIconButton
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonSize
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonType
import com.desh2403.bento_ds_compose.uikit.component.info.BentoDSCard
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import java.time.LocalDateTime
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun Datepicker(
    year: Int,
    month: Int,
    dayOfMonth: Int,
    // month 0-11
    onSelectDay: (dayOfMonth: Int, month: Int, year: Int) -> Unit,
    showPresetsButtons : Boolean = false,
) {
    var selectedDay by remember { mutableIntStateOf(dayOfMonth) }

    var shownMonth by remember { mutableIntStateOf(month) }
    var shownYear by remember { mutableIntStateOf(year) }

    val isLeapYear = remember(shownMonth) {
        (shownYear % 400 == 0 || shownYear % 4 == 0)
    }
    val shownMonthDaysCount = remember(shownMonth, shownYear) {
        LocalDateTime.of(shownYear, shownMonth + 1, 1, 0, 0)
            .month.length(isLeapYear)
    }
    val shownMonthFirstDayOfWeek = remember(shownMonth, shownYear) {
        LocalDateTime.of(shownYear, shownMonth + 1, 1, 0, 0)
            .dayOfWeek.value - 1
    }
    val onSelectDayOfWeek = { newDay: Int ->
        selectedDay = newDay
        onSelectDay(selectedDay, shownMonth, shownYear)
    }
    BentoDSCard {
        Column {
            DatepickerHeader(
                year = shownYear,
                month = shownMonth,
                onChangeDate = { newMonth, newYear ->
                    shownMonth = newMonth
                    shownYear = newYear
                }
            )
            // TODO дни недели
            var i = 0
            var weekStart = 1
            while (weekStart <= shownMonthDaysCount) {
                DatepickerWeek(
                    startDay = if (i == 0) 1 else weekStart,
                    startDayOfWeek = if (i == 0) shownMonthFirstDayOfWeek else 0,
                    endDayOfWeek = if (shownMonthDaysCount - weekStart > 6) 6
                    else shownMonthDaysCount - weekStart,
                    selectedDay = selectedDay,
                    onSelectDay = onSelectDayOfWeek,
                )
                weekStart += if (i == 0) 7 - shownMonthFirstDayOfWeek else 7
                i++
            }

            if (showPresetsButtons) {

                DatepickerPresetsButtons(
                    onThisMonthPressed = {},
                    onThisWeekPressed = {},
                    onThisYearPressed = {},
                )
            }
        }
    }
}

// TODO Unify preset buttons
@Composable
fun DatepickerPresetsButtons(
    onThisWeekPressed: () -> Unit,
    onThisMonthPressed: () -> Unit,
    onThisYearPressed: () -> Unit,
) {
    Row {
        BentoDSButton(
            text = "This week",
            buttonType = ButtonType.SECONDARY_TRANSPARENT,
            buttonSize = ButtonSize.S,
            onClick = onThisWeekPressed,
        )
        BentoDSButton(
            text = "This month",
            buttonType = ButtonType.SECONDARY_TRANSPARENT,
            buttonSize = ButtonSize.S,
            onClick = onThisWeekPressed,
        )
        BentoDSButton(
            text = "This year",
            buttonType = ButtonType.SECONDARY_TRANSPARENT,
            buttonSize = ButtonSize.S,
            onClick = onThisWeekPressed,
        )
    }
}

@Composable
fun DatepickerWeek(
    startDay: Int,
    startDayOfWeek: Int,
    endDayOfWeek: Int,
    selectedDay: Int? = null,
    onSelectDay: (Int) -> Unit,
) {
    Row {
        var day = startDay
        (0 until 7).forEach { dayNum ->
            val inRange = dayNum in startDayOfWeek..endDayOfWeek
            DatePickerDay(
                number = if (inRange) day else null,
                isSelected = inRange && day == selectedDay,
                //isWeekend = dayNum == 6 || dayNum == 5,
                onClick = { number -> if (inRange) onSelectDay(number) }
            )
            if (inRange) day++
        }
    }
}

@Composable
fun DatePickerDay(
    number: Int? = null,
    isToday: Boolean = false,
    isSelected: Boolean,
    //isWeekend: Boolean,
    onClick: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .background(
                color = when {
                    isSelected -> BentoDSTheme.colors.fg.interactive
                    //isWeekend -> Color.LightGray
                    else -> Color.Transparent
                },
                shape = BentoDSTheme.shapes.buttonShape,
            ) // TODO hover color
            .clickable(onClick = { number?.let { onClick(it) } }),
        contentAlignment = Alignment.Center,
    ) {
        number?.let {
            Text(
                text = number.toString(),
                color = if (isSelected)
                    BentoDSTheme.colors.primary.solidEnabledFg else
                    BentoDSTheme.colors.text.primary,
                style = if (isToday) {
                    BentoDSTheme.typography.bodyMediumStrong
                } else {
                    BentoDSTheme.typography.bodyMedium
                }
            )
        }
    }
}

private const val MIN_YEAR = 1900
private const val MAX_YEAR = 2100

@Composable
fun DatepickerHeader(
    year: Int,
    // month 0-11
    month: Int,
    // month 0-11
    onChangeDate: (month: Int, year: Int) -> Unit,
) {
    var selectedYear by remember { mutableIntStateOf(year) }
    var selectedMonth by remember { mutableIntStateOf(month) }
    val monthItems = remember {
        (1..12).map {
            Month.of(it).getDisplayName(
                TextStyle.FULL_STANDALONE, Locale.forLanguageTag("ru")
            )
        }
    }
    val yearItems = remember { (MIN_YEAR..MAX_YEAR).map { it.toString() } }

    val decreaseMonth = remember {
        {
            if (selectedMonth == 0) {
                selectedYear -= 1
                selectedMonth = 11
            } else {
                selectedMonth -= 1
            }
            onChangeDate(selectedMonth, selectedYear)
        }
    }

    val increaseMonth = remember {
        {
            if (selectedMonth == 11) {
                selectedYear += 1
                selectedMonth = 0
            } else {
                selectedMonth += 1
            }
            onChangeDate(selectedMonth, selectedYear)
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
        BentoDSIconButton(
            iconRes = R.drawable.ic_chevron_left,
            buttonType = ButtonType.SECONDARY_TRANSPARENT,
            onClick = decreaseMonth,
        )
        DropdownSelector(
            selectedItem = selectedMonth,
            items = monthItems,
            onItemSelected = { newMonthIndex ->
                selectedMonth = newMonthIndex
                onChangeDate(selectedMonth, selectedYear)
            })
        DropdownSelector(
            selectedItem = yearItems.indexOf(year.toString()),
            items = yearItems,
            onItemSelected = { newYearIndex ->
                selectedYear = yearItems[newYearIndex].toInt()
                onChangeDate(selectedMonth, selectedYear)
            }
        )
        // TODO right button size: iconSize + iconButtonSize
        BentoDSIconButton(
            iconRes = R.drawable.ic_chevron_right,
            buttonType = ButtonType.SECONDARY_TRANSPARENT,
            onClick = increaseMonth
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DatepickerPreview() {
    BentoDSTheme {
        Datepicker(
            year = 2024,
            month = 0,
            dayOfMonth = 0,
            onSelectDay = { _, _, _ -> },
            showPresetsButtons = true,
        )
    }
}