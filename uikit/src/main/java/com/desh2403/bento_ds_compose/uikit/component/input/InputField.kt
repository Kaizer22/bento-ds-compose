package com.desh2403.bento_ds_compose.uikit.component.input

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.FSpace
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

enum class InputFieldType {
    TEXT, TEXT_AREA, PASSWORD, NUMBER, NUMBER_DECIMAL, TIME,
    DATE, SINGLE_SELECTION, MULTI_SELECTION
}

// TODO анимации переходов между состояниями
enum class InputFieldState {
    ENABLED, ENABLED_ERROR,
    DISABLED,
    READ_ONLY, READ_ONLY_ERROR,
}

internal val ERROR_STATES = arrayOf(
    InputFieldState.ENABLED_ERROR,
    InputFieldState.READ_ONLY_ERROR
)

private val DISABLE_INPUT_STATE = arrayOf(
    InputFieldState.DISABLED, InputFieldState.READ_ONLY_ERROR,
    InputFieldState.READ_ONLY
)

data class InputFieldIcon(
    @DrawableRes val iconRes: Int,
    @DrawableRes val iconSecondStateRes: Int? = null,
    val onClick: (() -> Unit)? = null,
)

private const val DEFAULT_MAX_LINES_COUNT = 1

private fun mapTypeToInputType(inputFieldType: InputFieldType) = when (inputFieldType) {
    InputFieldType.TEXT -> KeyboardType.Text
    InputFieldType.TEXT_AREA -> KeyboardType.Text
    InputFieldType.PASSWORD -> KeyboardType.Password
    InputFieldType.NUMBER -> KeyboardType.Number
    InputFieldType.NUMBER_DECIMAL -> KeyboardType.Decimal
    InputFieldType.TIME, InputFieldType.DATE,
    InputFieldType.SINGLE_SELECTION, InputFieldType.MULTI_SELECTION,
    -> KeyboardType.Decimal
}

@Composable
private fun getInputPaletteByState(state: InputFieldState) = when (state) {
    InputFieldState.ENABLED -> BentoDSTheme.inputStatesColors.enabledStatePalette
    InputFieldState.ENABLED_ERROR -> BentoDSTheme.inputStatesColors.enabledErrorPalette
    InputFieldState.DISABLED -> BentoDSTheme.inputStatesColors.disabledPalette
    InputFieldState.READ_ONLY -> BentoDSTheme.inputStatesColors.readOnlyPalette
    InputFieldState.READ_ONLY_ERROR -> BentoDSTheme.inputStatesColors.readOnlyErrorPalette
}

@Composable
fun BentoDSInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholder: String? = null,
    assistiveText: String? = null,
    infoMessage: String? = null,
    displayUnit: String? = null,
    type: InputFieldType = InputFieldType.TEXT,
    maxLinesCount: Int = DEFAULT_MAX_LINES_COUNT,
    state: InputFieldState = InputFieldState.ENABLED,
    leadingIcon: InputFieldIcon? = null,
    trailingIcon: InputFieldIcon? = null,
) {
    var input by remember { mutableStateOf(value) }
    var isFocused by remember { mutableStateOf(false) }

    val isError = remember(state) { ERROR_STATES.contains(state) }
    val isEnable = remember(state) { !DISABLE_INPUT_STATE.contains(state) }
    val showInfoButton = remember(infoMessage) { infoMessage != null }

    val linesCount = remember(maxLinesCount) {
        if (type == InputFieldType.TEXT_AREA) maxLinesCount else
            DEFAULT_MAX_LINES_COUNT
    }

    val inputPalette = getInputPaletteByState(state = state)

    val inputType = remember { mapTypeToInputType(type) }

    when (type) {
        InputFieldType.TEXT, InputFieldType.TEXT_AREA, InputFieldType.PASSWORD,
        InputFieldType.NUMBER, InputFieldType.NUMBER_DECIMAL, InputFieldType.TIME,
        -> {
            BasicTextField(
                modifier = modifier.onFocusChanged {
                    isFocused = it.isFocused && state == InputFieldState.ENABLED
                },
                value = input,
                enabled = isEnable,
                onValueChange = {
                    input = it
                    onValueChange.invoke(input)
                },
                textStyle = BentoDSTheme.typography.bodyLarge.copy(
                    color = inputPalette.inputTextColor,
                ),
                maxLines = linesCount,
                minLines = linesCount,
                keyboardOptions = KeyboardOptions(
                    keyboardType = inputType,
                ),
                decorationBox = { innerTextField ->
                    Column {
                        InputHeader(
                            label = label,
                            showInfoButton = showInfoButton,
                            infoIconTint = inputPalette.infoIconTint,
                        )
                        VSpace(h = BentoDSTheme.dimensions.x1)
                        Box(
                            modifier = Modifier
                                .background(
                                    color = inputPalette.fieldBackgroundColor,
                                    shape = BentoDSTheme.shapes.buttonShape
                                )
                                .border(
                                    width = 1.dp,
                                    color = if (state == InputFieldState.ENABLED && isFocused)
                                        BentoDSTheme.colors.outlineInputFocus
                                    else inputPalette.borderColor,
                                    shape = BentoDSTheme.shapes.buttonShape
                                )
                                .padding(
                                    horizontal = BentoDSTheme.dimensions.x4,
                                    vertical = BentoDSTheme.dimensions.x3,
                                )
                                .fillMaxWidth()
                        ) {
                            Row {
                                leadingIcon?.let {
                                    // TODO interactive state icon button
                                    Icon(
                                        modifier = Modifier.size(
                                            BentoDSTheme.dimensions.x6,
                                        ),
                                        tint = inputPalette.customIconsTint,
                                        painter = painterResource(id = it.iconRes),
                                        contentDescription = null,
                                    )
                                    HSpace(w = BentoDSTheme.dimensions.x4)
                                }
                                Box {
                                    if (input.isEmpty() && placeholder != null) {
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
                                displayUnit?.let {
                                    HSpace(w = BentoDSTheme.dimensions.x4)
                                    Text(
                                        text = it,
                                        style = BentoDSTheme.typography.labelLarge,
                                        color = inputPalette.assistTextColor,
                                    )
                                }
                                trailingIcon?.let {
                                    HSpace(w = BentoDSTheme.dimensions.x4)
                                    // TODO interactive state icon button
                                    Icon(
                                        modifier = Modifier.size(
                                            BentoDSTheme.dimensions.x6,
                                        ),
                                        tint = inputPalette.customIconsTint,
                                        painter = painterResource(id = it.iconRes),
                                        contentDescription = null,
                                    )
                                }
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

        InputFieldType.DATE -> {}
        InputFieldType.MULTI_SELECTION, InputFieldType.SINGLE_SELECTION -> {}
    }
}

@Composable
@Preview(showSystemUi = true)
fun InputFieldPreview() {
    BentoDSTheme {
        Column {
            InputFieldState.entries.forEach { state ->
                BentoDSInputField(
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