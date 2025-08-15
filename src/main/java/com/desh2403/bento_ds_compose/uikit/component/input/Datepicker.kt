package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import com.desh2403.bento_ds_compose.uikit.component.info.CalendarDayBackground
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import com.desh2403.bento_ds_compose.uikit.utils.rememberNoRippleInteractionSource
import java.time.LocalDateTime
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun BentoDSDatepicker(
    year: Int,
    month: Int,
    dayOfMonth: Int,
    // month 0-11
    onSelectDay: (dayOfMonth: Int, month: Int, year: Int) -> Unit,
    weekDayProvider: ((dayOfWeek: Int) -> String)? = null,
    showPresetsButtons: Boolean = false,
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
    BentoDSCard(
        interactionSource = rememberNoRippleInteractionSource()
    ) {
        Column {
            DatepickerHeader(
                year = shownYear,
                month = shownMonth,
                onChangeDate = { newMonth, newYear ->
                    shownMonth = newMonth
                    shownYear = newYear
                }
            )
            DaysOfWeekRow(weekDayProvider)
            var i = 0
            var weekStart = 1
            while (weekStart <= shownMonthDaysCount) {
                DatepickerWeek(
                    startDay = if (i == 0) 1 else weekStart,
                    startDayOfWeek = if (i == 0) shownMonthFirstDayOfWeek else 0,
                    endDayOfWeek = if (shownMonthDaysCount - weekStart > 6) 6
                    else shownMonthDaysCount - weekStart,
                    dayContent = { day ->
                        val isSelected = day == selectedDay && year == shownYear
                                && month == shownMonth
                        DatePickerDay(
                            number = day,
                            textColor = if (isSelected)
                                BentoDSTheme.colors.primary.solidEnabledFg
                            else BentoDSTheme.colors.text.primary,
                            onClick = { onSelectDayOfWeek.invoke(day) },
                            dayBackground = DatepickerDayBackground(isSelected)
                        )
                    }
                )
                weekStart += if (i == 0) 7 - shownMonthFirstDayOfWeek else 7
                i++
            }

            if (showPresetsButtons) {
                DatepickerPresetsButtons(
                    onThisMonthPressed = {
                        val currentDatetime = LocalDateTime.now()
                        shownYear = currentDatetime.year
                        shownMonth = currentDatetime.monthValue
                    },
                    onThisWeekPressed = {},
                    onThisYearPressed = {
                        shownYear = LocalDateTime.now().year
                    },
                )
            }
        }
    }
}

data class DatepickerDayBackground(
    val isSelected: Boolean,
): CalendarDayBackground {
    @Composable
    override fun Composable() {
        Box {
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = BentoDSTheme.colors.fg.interactive,
                            shape = BentoDSTheme.shapes.buttonShape,
                        )
                ) {
                    Text("")
                }
            }
        }
    }
}

@Composable
fun DaysOfWeekRow(
    weekDayProvider: ((dayOfWeek: Int) -> String)?,
) {
    Row {
        (0 until 7).forEach { index ->
            DayOfWeekTitle(
                text = weekDayProvider?.invoke(index) ?: when (index) {
                    0 -> "M"
                    1 -> "T"
                    2 -> "W"
                    3 -> "T"
                    4 -> "F"
                    5 -> "S"
                    6 -> "S"
                    else -> ""
                }
            )
        }
    }
}

@Composable
fun DayOfWeekTitle(
    text: String,
) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .background(
                color = Color.Transparent,
                shape = BentoDSTheme.shapes.buttonShape,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = BentoDSTheme.colors.text.secondary,
            style = BentoDSTheme.typography.labelLarge,
        )
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
//        BentoDSButton(
//            text = "This week",
//            buttonType = ButtonType.SECONDARY_TRANSPARENT,
//            buttonSize = ButtonSize.S,
//            onClick = onThisWeekPressed,
//        )
        BentoDSButton(
            text = "This month",
            buttonType = ButtonType.SECONDARY_TRANSPARENT,
            buttonSize = ButtonSize.S,
            onClick = onThisMonthPressed,
        )
        BentoDSButton(
            text = "This year",
            buttonType = ButtonType.SECONDARY_TRANSPARENT,
            buttonSize = ButtonSize.S,
            onClick = onThisYearPressed,
        )
    }
}

@Composable
fun DatepickerWeek(
    // 0-30
    startDay: Int,
    // 0-6
    startDayOfWeek: Int,
    // 0-6
    endDayOfWeek: Int,
    dayContent: @Composable (dayOfWeek: Int) -> Unit,
) {
    Row {
        var day = startDay
        (0 until 7).forEach { dayNum ->
            val inRange = dayNum in startDayOfWeek..endDayOfWeek
            if (inRange) {
                dayContent.invoke(day)
                day++
            } else {
                EmptyDayBox()
            }
        }
    }
}

@Composable
fun EmptyDayBox() {
    Box(
        modifier = Modifier
            .size(44.dp)
            .background(
                color = Color.Transparent,
                shape = BentoDSTheme.shapes.buttonShape,
            ),
        contentAlignment = Alignment.Center,
    ) { Text(text = "") }
}

@Composable
fun DatePickerDay(
    number: Int? = null,
    textColor: Color = BentoDSTheme.colors.text.primary,
    //isSelected: Boolean,
    onClick: (Int) -> Unit,
    dayBackground: CalendarDayBackground? = null,
) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .clickable(onClick = { number?.let { onClick(it) } }),
        contentAlignment = Alignment.Center,
    ) {
        number?.let {
            dayBackground?.Composable()
            Text(
                text = number.toString(),
                color = textColor,
                style = BentoDSTheme.typography.bodyMedium,
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
    locale: String = Locale.getDefault().language,
) {
    var selectedYear by remember { mutableIntStateOf(year) }
    var selectedMonth by remember { mutableIntStateOf(month) }
    val monthItems = remember {
        (1..12).map {
            Month.of(it).getDisplayName(
                TextStyle.FULL_STANDALONE,
                Locale.forLanguageTag(locale),
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
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
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
        var selectedYear by remember { mutableStateOf(2025) }
        var selectedMonth by remember { mutableStateOf(6) }
        var selectedDayOfMonth by remember { mutableStateOf(14) }
        BentoDSDatepicker(
            year = selectedYear,
            month = selectedMonth,
            dayOfMonth = selectedDayOfMonth,
            onSelectDay = { dayOfMonth, month, year ->
                selectedYear = year
                selectedMonth = month
                selectedDayOfMonth = dayOfMonth
            },
            showPresetsButtons = true,
        )
    }
}