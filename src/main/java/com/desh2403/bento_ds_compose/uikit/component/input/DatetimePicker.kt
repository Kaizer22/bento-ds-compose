package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.time.LocalDateTime

/**
 * Date picker followed by a time picker.
 *
 * Bento DS has no combined date and time picker; see [DateField](https://developer.bento-ds.com/Components/DateField)
 * and [TimeField](https://developer.bento-ds.com/Components/TimeField). The whole component is an extension
 * of this library.
 *
 * @param initialDateTime date and time selected at first.
 * @param onSelectionChanged called with the new date and time after each change.
 */
@Composable
fun DatetimePicker(
    initialDateTime: LocalDateTime = LocalDateTime.now(),
    onSelectionChanged: (LocalDateTime) -> Unit,
) {
    var selectedDateTime by remember { mutableStateOf(initialDateTime.withSecond(0).withNano(0)) }

    Column {
        BentoDSDatepicker(
            year = selectedDateTime.year,
            month = selectedDateTime.monthValue - 1,
            dayOfMonth = selectedDateTime.dayOfMonth,
            onSelectDay = { newDay, newMonth, newYear ->
                selectedDateTime = selectedDateTime
                    .withDayOfMonth(1)
                    .withYear(newYear)
                    .withMonth(newMonth + 1)
                    .withDayOfMonth(newDay)
                onSelectionChanged(selectedDateTime)
            }
        )
        TimePicker(
            hour = selectedDateTime.hour,
            minute = selectedDateTime.minute,
            onTimeChanged = { selectedHour, selectedMinute ->
                selectedDateTime = selectedDateTime.withHour(selectedHour).withMinute(selectedMinute)
                onSelectionChanged(selectedDateTime)
            }
        )
    }
}
