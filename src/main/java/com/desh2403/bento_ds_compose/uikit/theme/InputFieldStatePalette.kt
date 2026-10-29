package com.desh2403.bento_ds_compose.uikit.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/** Colors of an input field in one [com.desh2403.bento_ds_compose.uikit.component.input.field.FieldState]. */
data class InputFieldStatePalette(
    val borderColor : Color,
    val labelColor : Color,
    val assistTextColor : Color,
    val placeholderColor : Color,
    val customIconsTint : Color,
    val infoIconTint: Color,
    val errorIconTint: Color,
    val fieldBackgroundColor : Color,
    val inputTextColor : Color,
)

/** Input field colors for every [com.desh2403.bento_ds_compose.uikit.component.input.field.FieldState]. */
data class InputFieldPalette(
    val enabledStatePalette: InputFieldStatePalette,
    val enabledErrorPalette: InputFieldStatePalette,

    val disabledPalette: InputFieldStatePalette,

    val readOnlyPalette: InputFieldStatePalette,
    val readOnlyErrorPalette: InputFieldStatePalette,
)

/** Default input field colors derived from the current [BentoDSTheme.colors]. */
@Composable
fun paInputStatePalettes() : InputFieldPalette {
    val enabledStatePalette = InputFieldStatePalette(
        borderColor = BentoDSTheme.colors.outline.inputEnabled,
        labelColor = BentoDSTheme.colors.text.secondary,
        assistTextColor = BentoDSTheme.colors.text.secondary,
        placeholderColor  = BentoDSTheme.colors.text.secondary,
        customIconsTint = BentoDSTheme.colors.fg.primary,
        fieldBackgroundColor = BentoDSTheme.colors.bg.elevated,
        inputTextColor = BentoDSTheme.colors.text.primary,
        infoIconTint = BentoDSTheme.colors.fg.secondary,
        errorIconTint = BentoDSTheme.colors.fg.negative,
    )
    val disabledPalette = InputFieldStatePalette(
        borderColor = BentoDSTheme.colors.fg.disabled,
        labelColor = BentoDSTheme.colors.fg.disabled,
        assistTextColor = BentoDSTheme.colors.fg.disabled,
        placeholderColor  = BentoDSTheme.colors.fg.disabled,
        customIconsTint = BentoDSTheme.colors.fg.disabled,
        fieldBackgroundColor = BentoDSTheme.colors.bg.primary,
        inputTextColor = BentoDSTheme.colors.fg.disabled,
        infoIconTint = BentoDSTheme.colors.fg.disabled,
        errorIconTint = BentoDSTheme.colors.fg.disabled,
    )
    val readOnlyPalette = InputFieldStatePalette(
        borderColor = Color.Transparent,
        labelColor = BentoDSTheme.colors.text.secondary,
        assistTextColor = BentoDSTheme.colors.text.secondary,
        placeholderColor  = BentoDSTheme.colors.text.secondary,
        customIconsTint = BentoDSTheme.colors.fg.secondary,
        fieldBackgroundColor = BentoDSTheme.colors.bg.secondary,
        inputTextColor = BentoDSTheme.colors.text.primary,
        infoIconTint = BentoDSTheme.colors.fg.secondary,
        errorIconTint = BentoDSTheme.colors.fg.negative,
    )
    return InputFieldPalette(
        enabledStatePalette = enabledStatePalette,
        enabledErrorPalette = enabledStatePalette.copy(
            borderColor = BentoDSTheme.colors.outline.negative,
        ),
        disabledPalette = disabledPalette,
        readOnlyPalette = readOnlyPalette,
        readOnlyErrorPalette = readOnlyPalette.copy(
            borderColor = BentoDSTheme.colors.outline.negative,
        ),
    )
}

