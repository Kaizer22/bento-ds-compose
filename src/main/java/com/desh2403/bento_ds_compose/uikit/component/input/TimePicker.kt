package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/**
 * Hour and minute selectors in the 24-hour format.
 *
 * Bento DS: [TimeField](https://developer.bento-ds.com/Components/TimeField). Bento DS has only the field;
 * this inline picker is an extension of this library. For a form field use
 * [com.desh2403.bento_ds_compose.uikit.component.input.field.BentoDSTimeField].
 *
 * @param hour selected hour, 0–23.
 * @param minute selected minute, 0–59.
 * @param onTimeChanged called with the new hour and minute.
 */
@Composable
fun TimePicker(
    hour: Int,
    minute: Int,
    onTimeChanged: (selectedHour: Int, selectedMinute: Int) -> Unit,
) {
    val hoursItems = remember { (0..23).map { it.toString() } }
    val minutesItems = remember { (0..59).map { "%02d".format(it) } }

    Row(verticalAlignment = Alignment.CenterVertically) {
        DropdownSelector(
            selectedItem = hour,
            items = hoursItems,
            onItemSelected = { newHour -> onTimeChanged(newHour, minute) },
        )
        HSpace(w = BentoDSTheme.dims.x2)
        DropdownSelector(
            selectedItem = minute,
            items = minutesItems,
            onItemSelected = { newMinute -> onTimeChanged(hour, newMinute) },
        )
    }
}
