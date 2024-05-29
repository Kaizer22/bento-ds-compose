package com.desh2403.bento_ds_compose.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.material3.lightColorScheme

@Composable
fun BentoDSTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkMode) paDarkColors() else paLightColors()


    val materialDarkColorPalette = darkColorScheme()
    val materialColorPalette = lightColorScheme()

    val materialColors = if (darkMode) {
        materialDarkColorPalette
    } else {
        materialColorPalette
    }
    CompositionLocalProvider(LocalBentoDSColors provides colors) {
        val buttonColors = paButtonColors()
        val inputStatePalettes = paInputStatePalettes()
        CompositionLocalProvider(
            LocalBentoDSButtonColors provides buttonColors,
            LocalPAInputFieldStateColors provides inputStatePalettes
        ) {
            MaterialTheme(
                colorScheme = materialColors,
                content = content,
            )
        }
    }
}


internal val LocalBentoDSColors = staticCompositionLocalOf<BentoDSColors> {
    error("CompositionLocal LocalPAColors not present")
}
internal val LocalBentoDSButtonColors = staticCompositionLocalOf<BentoDSButtonColors> {
    error("CompositionLocal LocalPAButtonColors not present")
}
internal val LocalPAInputFieldStateColors = staticCompositionLocalOf<InputFieldPalette> {
    error("CompositionLocal LocalPAInputFieldStateColors not present")
}

object BentoDSTheme {
    val colors: BentoDSColors
        @Composable
        @ReadOnlyComposable
        get() = LocalBentoDSColors.current

    val buttonsColors: BentoDSButtonColors
        @Composable
        @ReadOnlyComposable
        get() = LocalBentoDSButtonColors.current

    val inputStatesColors: InputFieldPalette
        @Composable
        @ReadOnlyComposable
        get() = LocalPAInputFieldStateColors.current

    val dimensions: BentoDSDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalBentoDSDimensions.current

    val shapes: BentoDSShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalBentoDSShapes.current

    val typography: BentoDSTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalBentoDSTypography.current
}