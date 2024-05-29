package com.desh2403.uikit.component.input

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.desh2403.uikit.component.HSpace

@Composable
fun TimePicker(
    // hour 0-23
    hour: Int,
    // minute 0-59,
    minute: Int,
    onTimeChanged: (selectedHour: Int, selectedMinute: Int) -> Unit,
) {
    val hoursItems = remember { (0..23).toList().map { it.toString() } }
    val minutesItems = remember { (0..59).toList().map { it.toString() } }
    var selectedMinute  by remember { mutableIntStateOf(minute) }
    var selectedHour by remember { mutableIntStateOf(hour) }

    Row {
        DropdownSelector(
            selectedItem = hour,
            items = hoursItems,
            onItemSelected = { hourItemIndex ->
                selectedHour = hourItemIndex
                onTimeChanged(selectedHour, selectedMinute)
            }
        )
        HSpace(w = 10.dp)
        DropdownSelector(
            selectedItem = minute,
            items = minutesItems,
            onItemSelected = { minuteItemIndex ->
                selectedMinute = minuteItemIndex
                onTimeChanged(selectedHour, selectedMinute)
            }
        )
    }
}