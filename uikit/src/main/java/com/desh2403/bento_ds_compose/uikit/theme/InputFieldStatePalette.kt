package com.desh2403.bento_ds_compose.uikit.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

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

data class InputFieldPalette(
    val enabledStatePalette: InputFieldStatePalette,
    val enabledErrorPalette: InputFieldStatePalette,

    val disabledPalette: InputFieldStatePalette,
    //val disabledErrorPalette: InputFieldStatePalette,

    val readOnlyPalette: InputFieldStatePalette,
    val readOnlyErrorPalette: InputFieldStatePalette,
)

@Composable
fun paInputStatePalettes() : InputFieldPalette {
    val enabledStatePalette = InputFieldStatePalette(
        borderColor = BentoDSTheme.colors.outlineInputEnabled,
        labelColor = BentoDSTheme.colors.textSecondary,
        assistTextColor = BentoDSTheme.colors.textSecondary,
        placeholderColor  = BentoDSTheme.colors.textSecondary,
        customIconsTint = BentoDSTheme.colors.fgPrimary,
        fieldBackgroundColor = BentoDSTheme.colors.bgPrimary,
        inputTextColor = BentoDSTheme.colors.textPrimary,
        infoIconTint = BentoDSTheme.colors.fgSecondary,
        errorIconTint = BentoDSTheme.colors.fgNegative,
    )
    val disabledPalette = InputFieldStatePalette(
        borderColor = BentoDSTheme.colors.fgDisabled,
        labelColor = BentoDSTheme.colors.fgDisabled,
        assistTextColor = BentoDSTheme.colors.fgDisabled,
        placeholderColor  = BentoDSTheme.colors.fgDisabled,
        customIconsTint = BentoDSTheme.colors.fgDisabled,
        fieldBackgroundColor = BentoDSTheme.colors.bgPrimary,
        inputTextColor = BentoDSTheme.colors.fgDisabled,
        infoIconTint = BentoDSTheme.colors.fgDisabled,
        errorIconTint = BentoDSTheme.colors.fgDisabled,
    )
    val readOnlyPalette = InputFieldStatePalette(
        borderColor = Color.Transparent,
        labelColor = BentoDSTheme.colors.textSecondary,
        assistTextColor = BentoDSTheme.colors.textSecondary,
        placeholderColor  = BentoDSTheme.colors.textSecondary,
        customIconsTint = BentoDSTheme.colors.fgSecondary,
        fieldBackgroundColor = BentoDSTheme.colors.bgSecondary,
        inputTextColor = BentoDSTheme.colors.textPrimary,
        infoIconTint = BentoDSTheme.colors.fgSecondary,
        errorIconTint = BentoDSTheme.colors.fgNegative,
    )
    return InputFieldPalette(
        enabledStatePalette = enabledStatePalette,
        enabledErrorPalette = enabledStatePalette.copy(
            borderColor = BentoDSTheme.colors.outlineNegative,
        ),
        disabledPalette = disabledPalette,
        readOnlyPalette = readOnlyPalette,
        // TODO
        readOnlyErrorPalette = readOnlyPalette,
    )
}

