package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.time.LocalDateTime

@Composable
fun DatetimePicker(
    onSelectionChanged: (LocalDateTime) -> Unit,
) {
    var selectedDateTime by remember { mutableStateOf(LocalDateTime.now()) }

    Column {
        Datepicker(
            year = selectedDateTime.year,
            month = selectedDateTime.month.value,
            dayOfMonth = selectedDateTime.dayOfMonth,
            onSelectDay = { newDay, newMonth, newYear ->
                with(selectedDateTime) {
                    // dayOfMonth 1-31
                    selectedDateTime = LocalDateTime
                        .of(newYear, newMonth, newDay, hour, minute, 0)
                    onSelectionChanged(selectedDateTime)
                }
            }
        )
        TimePicker(
            hour = selectedDateTime.hour,
            minute = selectedDateTime.minute,
            onTimeChanged = { selectedHour, selectedMinute ->
                with(selectedDateTime) {
                    selectedDateTime = LocalDateTime
                        .of(year, month, dayOfMonth, selectedHour, selectedMinute, 0)
                    onSelectionChanged(selectedDateTime)
                }
            }
        )
    }
}