package com.desh2403.bento_ds_compose.uikit.component.input.field

import android.app.TimePickerDialog
import android.text.format.DateFormat
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import java.time.LocalTime

/**
 * Field that shows a time and opens the system time picker dialog when clicked.
 * The dialog starts at the time in [value], or at the current time if [value] is not `H:mm`.
 *
 * Bento DS: [TimeField](https://developer.bento-ds.com/Components/TimeField)
 *
 * Differences from Bento DS: the value is a `H:mm` string, the time is picked in the system dialog,
 * and the hour cycle follows the device setting.
 *
 * @param modifier modifier of the input box.
 * @param label label above the field. Bento DS `label`.
 * @param assistiveText text under the field; an error message in the `_ERROR` states.
 * Bento DS `assistiveText` and `issues`.
 * @param value shown time in the `H:mm` format. Bento DS `value`.
 * @param onTimeChanged called with the picked time in the `H:mm` format. Bento DS `onChange`.
 * @param state enabled, disabled, read-only and error states; only the enabled states open the dialog.
 */
@Composable
fun BentoDSTimeField(
    modifier: Modifier = Modifier,
    label: String? = null,
    assistiveText: String? = null,
    value: String,
    onTimeChanged: (String) -> Unit,
    state: FieldState = FieldState.ENABLED,
) {
    val context = LocalContext.current
    val isEnabled = state == FieldState.ENABLED || state == FieldState.ENABLED_ERROR
    BentoDSTextField(
        inputFieldContentModifier = modifier
            .clip(BentoDSTheme.shapes.inputShape)
            .clickable(enabled = isEnabled, role = Role.Button) {
                val initial = parseTime(value) ?: LocalTime.now()
                TimePickerDialog(
                    context,
                    { _, hour: Int, minute: Int -> onTimeChanged("%d:%02d".format(hour, minute)) },
                    initial.hour,
                    initial.minute,
                    DateFormat.is24HourFormat(context),
                ).show()
            },
        value = value,
        readOnlyInputField = true,
        state = state,
        label = label,
        assistiveText = assistiveText,
        onValueChange = {},
    )
}

private fun parseTime(value: String): LocalTime? {
    val parts = value.split(":")
    if (parts.size != 2) return null
    val hour = parts[0].toIntOrNull() ?: return null
    val minute = parts[1].toIntOrNull() ?: return null
    return if (hour in 0..23 && minute in 0..59) LocalTime.of(hour, minute) else null
}
