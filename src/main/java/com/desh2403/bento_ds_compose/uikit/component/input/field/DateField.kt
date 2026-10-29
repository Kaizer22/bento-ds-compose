package com.desh2403.bento_ds_compose.uikit.component.input.field

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.FSpace
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.info.BentoDSCalendarDay
import com.desh2403.bento_ds_compose.uikit.component.info.BentoDSIcon
import com.desh2403.bento_ds_compose.uikit.component.info.BentoDSMonthCalendar
import com.desh2403.bento_ds_compose.uikit.component.info.CalendarDayStyle
import com.desh2403.bento_ds_compose.uikit.component.info.IconSize
import com.desh2403.bento_ds_compose.uikit.component.modal.BentoDSBottomSheet
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

private val DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy")

/**
 * Field that shows the selected date and opens a bottom sheet with a month calendar when clicked.
 * Selecting a day closes the sheet.
 *
 * Bento DS: [DateField](https://developer.bento-ds.com/Components/DateField)
 *
 * Differences from Bento DS: the calendar opens in a bottom sheet; range selection, shortcuts,
 * `shouldDisableDate` and `hint` are not supported. [pickerTitle] and [formatter] are extensions.
 *
 * @param value selected date. Bento DS `value`.
 * @param onValueChange called with the selected date. Bento DS `onChange`.
 * @param modifier modifier of the field.
 * @param label label above the field. Bento DS `label`.
 * @param pickerTitle title of the sheet with the calendar; [label] by default.
 * @param assistiveText text under the field; an error message in the `_ERROR` states.
 * Bento DS `assistiveText` and `issues`.
 * @param state enabled, disabled, read-only and error states; only the enabled states open the picker.
 * Bento DS `disabled`, `isReadOnly` and `issues`.
 * @param minDate earliest selectable day; earlier days are disabled. Bento DS `minDate`.
 * @param maxDate latest selectable day; later days are disabled. Bento DS `maxDate`.
 * @param formatter format of the shown date.
 */
@Composable
fun BentoDSDateField(
    value: LocalDate,
    onValueChange: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    pickerTitle: String? = label,
    assistiveText: String? = null,
    state: FieldState = FieldState.ENABLED,
    minDate: LocalDate? = null,
    maxDate: LocalDate? = null,
    formatter: DateTimeFormatter = DEFAULT_DATE_FORMAT,
) {
    var showPicker by rememberSaveable { mutableStateOf(false) }
    val palette = getInputPaletteByState(state)
    val isEnabled = state == FieldState.ENABLED || state == FieldState.ENABLED_ERROR
    Column(modifier = modifier) {
        InputHeader(label = label, showInfoButton = false, infoIconTint = palette.infoIconTint)
        InputDecorationBox(
            modifier = Modifier
                .clip(BentoDSTheme.shapes.inputShape)
                .clickable(enabled = isEnabled, role = Role.Button) { showPicker = true },
            state = state,
            isFocused = showPicker,
            inputPalette = palette,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = value.format(formatter),
                    style = BentoDSTheme.typography.bodyLarge,
                    color = palette.inputTextColor,
                    maxLines = 1,
                )
                FSpace()
                BentoDSIcon(
                    iconRes = R.drawable.ic_calendar,
                    iconSize = IconSize.M,
                    iconTint = palette.customIconsTint,
                )
            }
        }
        InputAssistiveText(
            assistiveText = assistiveText,
            errorIconTint = palette.errorIconTint,
            isError = ERROR_STATES.contains(state),
        )
    }
    if (showPicker) {
        BentoDSBottomSheet(
            onDismissRequest = { showPicker = false },
            title = pickerTitle,
            skipPartiallyExpanded = true,
        ) {
            DatePickerCalendar(
                selected = value,
                minDate = minDate,
                maxDate = maxDate,
                onSelect = {
                    onValueChange(it)
                    showPicker = false
                },
            )
        }
    }
}

@Composable
private fun DatePickerCalendar(
    selected: LocalDate,
    minDate: LocalDate?,
    maxDate: LocalDate?,
    onSelect: (LocalDate) -> Unit,
) {
    val today = LocalDate.now()
    var month by rememberSaveable { mutableStateOf(YearMonth.from(selected)) }
    BentoDSMonthCalendar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = BentoDSTheme.dims.x2),
        month = month,
        onMonthChange = { month = it },
        canGoPrevious = minDate == null || month > YearMonth.from(minDate),
        canGoNext = maxDate == null || month < YearMonth.from(maxDate),
        dayProvider = { date ->
            val outOfRange = (minDate != null && date < minDate) || (maxDate != null && date > maxDate)
            BentoDSCalendarDay(
                style = when {
                    outOfRange -> CalendarDayStyle.DISABLED
                    date == selected -> CalendarDayStyle.FILLED
                    else -> CalendarDayStyle.PLAIN
                },
                isHighlighted = date == today && date != selected,
            )
        },
        onDayClick = onSelect,
    )
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun DateFieldPreview() {
    BentoDSTheme {
        Row(modifier = Modifier.padding(BentoDSTheme.dims.x4)) {
            BentoDSDateField(
                modifier = Modifier.weight(1f),
                label = "From",
                value = LocalDate.of(2026, 7, 15),
                onValueChange = {},
            )
            HSpace(BentoDSTheme.dims.x2)
            BentoDSDateField(
                modifier = Modifier.weight(1f),
                label = "To",
                value = LocalDate.of(2026, 10, 15),
                onValueChange = {},
                state = FieldState.DISABLED,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun DatePickerCalendarPreview() {
    BentoDSTheme {
        Column(modifier = Modifier.padding(BentoDSTheme.dims.x4)) {
            DatePickerCalendar(
                selected = LocalDate.of(2026, 7, 15),
                minDate = null,
                maxDate = LocalDate.of(2026, 7, 21),
                onSelect = {},
            )
        }
    }
}
