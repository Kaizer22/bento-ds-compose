package com.desh2403.bento_ds_compose.uikit.component.input.field

import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import java.util.Calendar

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

    val calendar = remember { Calendar.getInstance() }
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]


    val timePickerDialog = TimePickerDialog(
        context,
        { _, selectedHour: Int, selectedMinute: Int ->
            onTimeChanged.invoke(
                "%d:%02d".format(selectedHour, selectedMinute)
            )
        },
        hour,
        minute,
        true // is24HoursView
    )
    BentoDSTextField(
        inputFieldContentModifier = modifier
            .clip(BentoDSTheme.shapes.buttonShape)
            .clickable(
                onClick = { timePickerDialog.show() }
            ),
        value = value,
        readOnlyInputField = true,
        state = state,
        label = label,
        assistiveText = assistiveText,
        onValueChange = {},
    )
}