package com.desh2403.bento_ds_compose.uikit.component.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.component.input.DatePickerDay
import com.desh2403.bento_ds_compose.uikit.component.input.DatepickerHeader
import com.desh2403.bento_ds_compose.uikit.component.input.DatepickerWeek
import com.desh2403.bento_ds_compose.uikit.component.input.DaysOfWeekRow
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import com.desh2403.bento_ds_compose.uikit.utils.rememberNoRippleInteractionSource
import java.time.LocalDateTime
import java.util.Locale

interface CalendarDayBackground {
    @Composable
    fun Composable()
}

@Composable
fun BentoDSMonthCalendar(
    year: Int,
    month: Int,
    onChangeDate: (newMonth: Int, newYear: Int) -> Unit,
    onDayClick: (dayOfMonth: Int, month: Int, year: Int) -> Unit,
    locale: String = Locale.getDefault().language,
    weekDayProvider: ((dayOfWeek: Int) -> String)? = null,
    dayTextColorProvider: ((dayOfMonth: Int, month: Int, year: Int) -> Color)? = null,
    dayBackgroundProvider: ((dayOfMonth: Int, month: Int, year: Int) -> CalendarDayBackground?)? = null
) {
    val isLeapYear = remember(month) {
        (year % 400 == 0 || year % 4 == 0)
    }
    val shownMonthDaysCount = remember(month, year) {
        LocalDateTime.of(year, month + 1, 1, 0, 0).month.length(isLeapYear)
    }
    val shownMonthFirstDayOfWeek = remember(month, year) {
        LocalDateTime.of(year, month + 1, 1, 0, 0).dayOfWeek.value - 1
    }
    BentoDSCard(
        interactionSource = rememberNoRippleInteractionSource()
    ) {
        Column {
            DatepickerHeader(
                year = year,
                month = month,
                onChangeDate = onChangeDate,
                locale = locale,
            )
            DaysOfWeekRow(weekDayProvider)
            var i = 0
            var weekStart = 1
            while (weekStart <= shownMonthDaysCount) {
                DatepickerWeek(
                    startDay = weekStart,
                    startDayOfWeek = if (i == 0) shownMonthFirstDayOfWeek else 0,
                    endDayOfWeek = if (shownMonthDaysCount - weekStart > 6) 6
                    else shownMonthDaysCount - weekStart,
                    dayContent = { day ->
                        DatePickerDay(
                            number = day,
                            textColor = dayTextColorProvider?.invoke(day, month, year)
                                ?: BentoDSTheme.colors.text.primary,
                            onClick = { onDayClick.invoke(day, month, year) },
                            dayBackground = dayBackgroundProvider?.invoke(day, month, year)
                        )
                    })
                weekStart += if (i == 0) 7 - shownMonthFirstDayOfWeek else 7
                i++
            }

//            if (showPresetsButtons) {
//                DatepickerPresetsButtons(
//                    onThisMonthPressed = {
//                        val currentDatetime = LocalDateTime.now()
//                        shownYear = currentDatetime.year
//                        shownMonth = currentDatetime.monthValue
//                    },
//                    onThisWeekPressed = {},
//                    onThisYearPressed = {
//                        shownYear = LocalDateTime.now().year
//                    },
//                )
//            }
        }
    }
}



class TestDayBg : CalendarDayBackground {
    @Composable
    override fun Composable() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Green, shape = BentoDSTheme.shapes.roundShape)
        ) {}
    }
}

@Composable
@Preview(showBackground = true)
fun MonthCalendarPreview() {
    BentoDSTheme {
        BentoDSMonthCalendar(
            year = 2025,
            month = 7,
            onChangeDate = { _, _ -> },
            onDayClick = { _, _, _ -> },
            dayBackgroundProvider = { d, m, y ->
                if (d == 10) {
                    TestDayBg()
                } else null
            },
        )
    }
}