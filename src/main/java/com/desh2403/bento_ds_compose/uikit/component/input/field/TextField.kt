package com.desh2403.bento_ds_compose.uikit.component.input.field

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import com.desh2403.bento_ds_compose.uikit.theme.InputFieldStatePalette

/**
 * State of an input field. Bento DS `disabled`, `isReadOnly` and `issues`:
 * the `_ERROR` states show the assistive text as an error.
 */
enum class FieldState {
    ENABLED, ENABLED_ERROR,
    DISABLED,
    READ_ONLY, READ_ONLY_ERROR,
}

internal val ERROR_STATES = arrayOf(
    FieldState.ENABLED_ERROR,
    FieldState.READ_ONLY_ERROR
)

private val DISABLE_INPUT_STATE = arrayOf(
    FieldState.DISABLED, FieldState.READ_ONLY_ERROR,
    FieldState.READ_ONLY
)

/**
 * Icon inside an input field.
 *
 * @param iconRes drawable of the icon.
 * @param onClick called when the icon is clicked; `null` makes the icon decorative.
 * @param contentDescription accessibility label of a clickable icon.
 */
data class InputFieldIcon(
    @DrawableRes val iconRes: Int,
    val onClick: (() -> Unit)? = null,
    val contentDescription: String? = null,
)

private const val DEFAULT_MAX_LINES_COUNT = 1

private val INPUT_BORDER = 1.dp
private val INPUT_FOCUSED_BORDER = 2.dp

@Composable
internal fun getInputPaletteByState(state: FieldState) = when (state) {
    FieldState.ENABLED -> BentoDSTheme.inputStatesColors.enabledStatePalette
    FieldState.ENABLED_ERROR -> BentoDSTheme.inputStatesColors.enabledErrorPalette
    FieldState.DISABLED -> BentoDSTheme.inputStatesColors.disabledPalette
    FieldState.READ_ONLY -> BentoDSTheme.inputStatesColors.readOnlyPalette
    FieldState.READ_ONLY_ERROR -> BentoDSTheme.inputStatesColors.readOnlyErrorPalette
}

/**
 * Text field with a label, an info hint, a placeholder, icons, a unit and an assistive or error text.
 * With [maxLinesCount] above 1 it works as a text area that grows from [minLinesCount] lines.
 *
 * Bento DS: [TextField](https://developer.bento-ds.com/Components/TextField),
 * [TextArea](https://developer.bento-ds.com/Components/TextArea)
 *
 * Extensions over Bento DS: [leadingIcon], [displayUnit] and [visualTransformation].
 * Bento DS `type` maps to [keyboardType] and, for passwords, to [visualTransformation].
 *
 * @param modifier modifier of the whole field, including the label and the assistive text.
 * @param inputFieldContentModifier modifier of the input box only.
 * @param value current text. Bento DS `value`.
 * @param onValueChange called with the new text. Bento DS `onChange`.
 * @param label label above the field. Bento DS `label`.
 * @param placeholder text shown while the field is empty. Bento DS `placeholder`.
 * @param assistiveText text under the field; an error message in the `_ERROR` states.
 * Bento DS `assistiveText` and `issues`.
 * @param infoMessage hint shown in a tooltip from the info button next to the label. Bento DS `hint`.
 * @param displayUnit unit after the text, e.g. `%`.
 * @param keyboardType keyboard of the field. Bento DS `type`.
 * @param visualTransformation transformation of the shown text, e.g. password masking.
 * @param maxLinesCount maximum number of visible lines. Bento DS TextArea `rows`.
 * @param minLinesCount minimum number of visible lines, at most [maxLinesCount].
 * @param state enabled, disabled, read-only and error states. Bento DS `disabled`, `isReadOnly` and `issues`.
 * @param readOnlyInputField makes the text read-only while keeping the enabled look, e.g. for a field
 * that opens a picker.
 * @param leadingIcon icon before the text.
 * @param trailingIcon icon after the text. Bento DS `rightAccessory`.
 */
@Composable
fun BentoDSTextField(
    modifier: Modifier = Modifier,
    inputFieldContentModifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholder: String? = null,
    assistiveText: String? = null,
    infoMessage: String? = null,
    displayUnit: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLinesCount: Int = DEFAULT_MAX_LINES_COUNT,
    minLinesCount: Int = 1,
    state: FieldState = FieldState.ENABLED,
    readOnlyInputField: Boolean = false,
    leadingIcon: InputFieldIcon? = null,
    trailingIcon: InputFieldIcon? = null,
) {
    var isFocused by remember { mutableStateOf(false) }

    val isError = state in ERROR_STATES
    val isEnable = state !in DISABLE_INPUT_STATE

    val inputPalette = getInputPaletteByState(state = state)

    BasicTextField(
        modifier = modifier.onFocusChanged {
            isFocused = it.isFocused && state == FieldState.ENABLED
        },
        value = value,
        enabled = isEnable,
        onValueChange = onValueChange,
        textStyle = BentoDSTheme.typography.bodyLarge.copy(
            color = inputPalette.inputTextColor,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
        ),
        visualTransformation = visualTransformation,
        readOnly = readOnlyInputField,
        maxLines = maxLinesCount,
        minLines = minLinesCount.coerceIn(1, maxLinesCount),
        cursorBrush = SolidColor(BentoDSTheme.colors.text.primary),
        decorationBox = { innerTextField ->
            Column {
                InputHeader(
                    label = label,
                    showInfoButton = infoMessage != null,
                    infoIconTint = inputPalette.infoIconTint,
                    infoMessage = infoMessage,
                )
                InputDecorationBox(
                    modifier = inputFieldContentModifier,
                    state = state,
                    isFocused = isFocused,
                    inputPalette = inputPalette,
                ) {
                    Row {
                        TextFieldIcon(
                            icon = leadingIcon,
                            inputPalette = inputPalette,
                            isLeading = true,
                        )
                        Box(modifier = Modifier.weight(1f)) {
                            if (value.isEmpty() && placeholder != null) {
                                Text(
                                    text = placeholder,
                                    style = BentoDSTheme.typography.bodyLarge,
                                    color = inputPalette.placeholderColor
                                )
                            }

                            innerTextField.invoke()
                        }
                        TextFieldDisplayUnit(
                            displayUnit = displayUnit,
                            inputPalette = inputPalette,
                        )
                        TextFieldIcon(
                            icon = trailingIcon,
                            inputPalette = inputPalette,
                            isLeading = false,
                        )
                    }
                }

                InputAssistiveText(
                    assistiveText = assistiveText,
                    errorIconTint = inputPalette.errorIconTint,
                    isError = isError,
                )
            }
        }
    )
}

/** Unit text shown after the value of an input field. */
@Composable
fun TextFieldDisplayUnit(
    displayUnit: String?,
    inputPalette: InputFieldStatePalette,
) {
    displayUnit?.let {
        HSpace(w = BentoDSTheme.dims.x4)
        Text(
            text = it,
            style = BentoDSTheme.typography.labelLarge,
            color = inputPalette.assistTextColor,
        )
    }
}

/** Leading or trailing [InputFieldIcon] of an input field. */
@Composable
fun TextFieldIcon(
    icon: InputFieldIcon?,
    inputPalette: InputFieldStatePalette,
    isLeading: Boolean,
) {
    icon?.let {
        if (!isLeading) HSpace(w = BentoDSTheme.dims.x4)
        Icon(
            modifier = Modifier
                .size(BentoDSTheme.dims.x6)
                .then(
                    if (it.onClick != null) Modifier.clickable(role = Role.Button, onClick = it.onClick)
                    else Modifier
                ),
            tint = inputPalette.customIconsTint,
            painter = painterResource(id = it.iconRes),
            contentDescription = if (it.onClick != null) it.contentDescription else null,
        )
        if (isLeading) HSpace(w = BentoDSTheme.dims.x4)
    }
}

/** Background and border of an input field box, reflecting its [state] and focus. */
@Composable
fun InputDecorationBox(
    modifier: Modifier = Modifier,
    state: FieldState,
    isFocused: Boolean,
    inputPalette: InputFieldStatePalette,
    content: @Composable () -> Unit,
) {
    val isActive = state == FieldState.ENABLED && isFocused
    Box(
        modifier = modifier
            .background(
                color = inputPalette.fieldBackgroundColor,
                shape = BentoDSTheme.shapes.inputShape
            )
            .border(
                width = if (isActive) INPUT_FOCUSED_BORDER else INPUT_BORDER,
                color = if (isActive) BentoDSTheme.colors.outline.inputFocus
                else inputPalette.borderColor,
                shape = BentoDSTheme.shapes.inputShape
            )
            .padding(
                horizontal = BentoDSTheme.dims.x4,
                vertical = BentoDSTheme.dims.x3,
            )
            .fillMaxWidth()
    ) {
        content.invoke()
    }
}

@Composable
@Preview(showSystemUi = true)
private fun InputFieldPreview() {
    BentoDSTheme {
        Column {
            FieldState.entries.forEach { state ->
                BentoDSTextField(
                    value = "Value",
                    label = "Label",
                    infoMessage = "Test",
                    placeholder = "Placeholder...",
                    displayUnit = "%",
                    assistiveText = "Assistive text",
                    onValueChange = {},
                    state = state,
                    leadingIcon = InputFieldIcon(
                        iconRes = R.drawable.ic_negative_solid,
                    ),
                    trailingIcon = InputFieldIcon(
                        iconRes = R.drawable.ic_negative_solid,
                    )
                )
            }
        }
    }
}