package com.desh2403.bento_ds_compose.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Root theme of the library. Provides Bento DS design tokens to every component below it
 * and maps them onto a Material 3 [MaterialTheme], so stock Material components inside
 * the tree pick up Bento colors instead of the Material defaults.
 *
 * Bento DS: [Theming](https://developer.bento-ds.com/Customization/theming)
 *
 * @param darkMode whether [darkColors] are used instead of [lightColors]. Follows the system setting by default.
 * @param lightColors color roles for the light mode.
 * @param darkColors color roles for the dark mode.
 * @param typography text styles, see [BentoDSTypography].
 * @param dims spacing and elevation scale, see [BentoDSDimensions].
 * @param shapes corner shapes of the components, see [BentoDSShapes].
 * @param motion durations and easings of the component animations, see [BentoDSMotion].
 * @param content the themed content.
 */
@Composable
fun BentoDSTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    lightColors: BentoDSPalette = paLightColors(),
    darkColors: BentoDSPalette = paDarkColors(),
    typography: BentoDSTypography = BentoDSTypography(),
    dims: BentoDSDimensions = BentoDSDimensions(),
    shapes: BentoDSShapes = BentoDSShapes(),
    motion: BentoDSMotion = BentoDSMotion(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkMode) darkColors else lightColors
    CompositionLocalProvider(
        LocalBentoDSColors provides colors,
        LocalBentoDSTypography provides typography,
        LocalBentoDSDimensions provides dims,
        LocalBentoDSShapes provides shapes,
        LocalBentoDSMotion provides motion,
    ) {
        val buttonColors = paButtonColors()
        val inputStatePalettes = paInputStatePalettes()
        CompositionLocalProvider(
            LocalBentoDSButtonColors provides buttonColors,
            LocalPAInputFieldStateColors provides inputStatePalettes
        ) {
            MaterialTheme(
                colorScheme = colors.toMaterialColorScheme(darkMode),
                content = content,
            )
        }
    }
}

private fun BentoDSPalette.toMaterialColorScheme(darkMode: Boolean): ColorScheme {
    val base = if (darkMode) darkColorScheme() else lightColorScheme()
    return base.copy(
        primary = primary.solidEnabledBg,
        onPrimary = primary.solidEnabledFg,
        primaryContainer = brand.tertiary,
        onPrimaryContainer = text.interactive,
        inversePrimary = brand.secondary,
        secondary = secondary.solidEnabledFg,
        onSecondary = secondary.solidEnabledBg,
        secondaryContainer = brand.tertiary,
        onSecondaryContainer = text.interactive,
        tertiary = fg.informative,
        onTertiary = fg.onInteractive,
        tertiaryContainer = bg.informative,
        onTertiaryContainer = text.informative,
        background = bg.primary,
        onBackground = text.primary,
        surface = bg.primary,
        onSurface = text.primary,
        surfaceVariant = bg.secondary,
        onSurfaceVariant = text.secondary,
        surfaceTint = bg.primary,
        inverseSurface = text.primary,
        inverseOnSurface = text.primaryInverse,
        error = fg.negative,
        onError = fg.onInteractive,
        errorContainer = bg.negative,
        onErrorContainer = text.negative,
        outline = outline.inputEnabled,
        outlineVariant = outline.decorative,
        scrim = bg.scrim,
        surfaceBright = bg.elevated,
        surfaceDim = bg.sunken,
        surfaceContainerLowest = bg.elevated,
        surfaceContainerLow = bg.elevated,
        surfaceContainer = bg.elevated,
        surfaceContainerHigh = bg.elevated,
        surfaceContainerHighest = bg.secondary,
    )
}

internal val LocalBentoDSColors = staticCompositionLocalOf<BentoDSPalette> {
    error("BentoDSTheme is missing: wrap the content in BentoDSTheme")
}
internal val LocalBentoDSButtonColors = staticCompositionLocalOf<BentoDSButtonColors> {
    error("BentoDSTheme is missing: wrap the content in BentoDSTheme")
}
internal val LocalPAInputFieldStateColors = staticCompositionLocalOf<InputFieldPalette> {
    error("BentoDSTheme is missing: wrap the content in BentoDSTheme")
}

/** Accessors for the tokens provided by the nearest [BentoDSTheme] composable. */
object BentoDSTheme {
    val colors: BentoDSPalette
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

    val dims: BentoDSDimensions
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

    val motion: BentoDSMotion
        @Composable
        @ReadOnlyComposable
        get() = LocalBentoDSMotion.current
}
