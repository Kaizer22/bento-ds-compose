package com.desh2403.bento_ds_compose.uikit.component.input.field

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.FSpace
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import com.desh2403.bento_ds_compose.uikit.theme.InputFieldStatePalette

// TODO анимации переходов между состояниями
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

data class InputFieldIcon(
    @DrawableRes val iconRes: Int,
    @DrawableRes val iconSecondStateRes: Int? = null,
    val onClick: (() -> Unit)? = null,
)

private const val DEFAULT_MAX_LINES_COUNT = 1

//private fun mapTypeToInputType(inputFieldType: InputFieldType) = when (inputFieldType) {
//    InputFieldType.TEXT -> KeyboardType.Text
//    InputFieldType.TEXT_AREA -> KeyboardType.Text
//    InputFieldType.PASSWORD -> KeyboardType.Password
//    InputFieldType.NUMBER -> KeyboardType.Number
//    InputFieldType.NUMBER_DECIMAL -> KeyboardType.Decimal
//    InputFieldType.TIME, InputFieldType.DATE,
//    InputFieldType.SINGLE_SELECTION, InputFieldType.MULTI_SELECTION,
//    -> KeyboardType.Decimal
//}

@Composable
private fun getInputPaletteByState(state: FieldState) = when (state) {
    FieldState.ENABLED -> BentoDSTheme.inputStatesColors.enabledStatePalette
    FieldState.ENABLED_ERROR -> BentoDSTheme.inputStatesColors.enabledErrorPalette
    FieldState.DISABLED -> BentoDSTheme.inputStatesColors.disabledPalette
    FieldState.READ_ONLY -> BentoDSTheme.inputStatesColors.readOnlyPalette
    FieldState.READ_ONLY_ERROR -> BentoDSTheme.inputStatesColors.readOnlyErrorPalette
}

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
    state: FieldState = FieldState.ENABLED,
    readOnlyInputField: Boolean = false,
    leadingIcon: InputFieldIcon? = null,
    trailingIcon: InputFieldIcon? = null,
) {
    var isFocused by remember { mutableStateOf(false) }

    val isError = remember(state) { ERROR_STATES.contains(state) }
    val isEnable = remember(state) { !DISABLE_INPUT_STATE.contains(state) }
    val showInfoButton = remember(infoMessage) { infoMessage != null }

    val inputPalette = getInputPaletteByState(state = state)

    BasicTextField(
        modifier = modifier.onFocusChanged {
            isFocused = it.isFocused && state == FieldState.ENABLED
        },
        value = value,
        enabled = isEnable,
        onValueChange = { newValue ->
            onValueChange.invoke(newValue)
        },
        textStyle = BentoDSTheme.typography.bodyLarge.copy(
            color = inputPalette.inputTextColor,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
        ),
        visualTransformation = visualTransformation,
        readOnly = readOnlyInputField,
        maxLines = maxLinesCount,
        minLines = maxLinesCount,
        cursorBrush = SolidColor(BentoDSTheme.colors.text.primary),
        decorationBox = { innerTextField ->
            Column {
                InputHeader(
                    label = label,
                    showInfoButton = showInfoButton,
                    infoIconTint = inputPalette.infoIconTint,
                )
                VSpace(h = BentoDSTheme.dims.x1)
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
                        Box {
                            if (value.isEmpty() && placeholder != null) {
                                Text(
                                    text = placeholder,
                                    style = BentoDSTheme.typography.bodyLarge,
                                    color = inputPalette.placeholderColor
                                )
                            }

                            innerTextField.invoke()
                        }
                        if (displayUnit != null || trailingIcon != null) {
                            FSpace()
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

@Composable
fun TextFieldIcon(
    icon: InputFieldIcon?,
    inputPalette: InputFieldStatePalette,
    isLeading: Boolean,
) {
    icon?.let {
        // TODO interactive state icon button
        if (!isLeading) HSpace(w = BentoDSTheme.dims.x4)
        Icon(
            modifier = Modifier.size(
                BentoDSTheme.dims.x6,
            ),
            tint = inputPalette.customIconsTint,
            painter = painterResource(id = it.iconRes),
            contentDescription = null,
        )
        if (isLeading) HSpace(w = BentoDSTheme.dims.x4)
    }
}


@Composable
fun InputDecorationBox(
    modifier: Modifier = Modifier,
    state: FieldState,
    isFocused: Boolean,
    inputPalette: InputFieldStatePalette,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = inputPalette.fieldBackgroundColor,
                shape = BentoDSTheme.shapes.buttonShape
            )
            .border(
                width = 1.dp,
                color = if (state == FieldState.ENABLED && isFocused)
                    BentoDSTheme.colors.outline.inputFocus
                else inputPalette.borderColor,
                shape = BentoDSTheme.shapes.buttonShape
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
fun InputFieldPreview() {
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