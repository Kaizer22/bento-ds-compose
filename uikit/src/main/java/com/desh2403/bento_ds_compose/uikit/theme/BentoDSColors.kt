package com.desh2403.bento_ds_compose.uikit.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Based on Free UI-Kit "Bento DS"
// https://uigarage.net/ui-kit/bento-ds-2-7/

// TODO move to UI-Kit lib
val bento_violet_1 = Color(0xFFEBF1FF)
val bento_violet_5 = Color(0xFFD0DCFF)
val bento_violet_10 = Color(0xFFB3BEFF)
val bento_violet_20 = Color(0xFF7F77FF)
val bento_violet_30 = Color(0xFF6741FC)
val bento_violet_40 = Color(0xFF5C17E5)
val bento_violet_50 = Color(0xFF4807B8)
val bento_violet_60 = Color(0xFF360090)
val bento_violet_70 = Color(0xFF2A0071)
val bento_violet_80 = Color(0xFF21005B)
val bento_violet_90 = Color(0xFF19004D)

val neutral_1 = Color(0xFFF6F8FB)
val neutral_5 = Color(0xFFECEFF4)
val neutral_10 = Color(0xFFDEE4ED)
val neutral_20 = Color(0xFFCBD4E1)
val neutral_30 = Color(0xFFB6C2D3)
val neutral_40 = Color(0xFF9AA8BC)
val neutral_50 = Color(0xFF728197)
val neutral_60 = Color(0xFF525E6F)
val neutral_70 = Color(0xFF3B4554)
val neutral_80 = Color(0xFF27313F)
val neutral_90 = Color(0xFF1A212B)

val informative_1 = Color(0xFFF2F7FF)
val informative_5 = Color(0xFFE0EDFF)
val informative_10 = Color(0xFFBDDAFF)
val informative_20 = Color(0xFF86B8FE)
val informative_30 = Color(0xFF5598F6)
val informative_40 = Color(0xFF2D7AE5)
val informative_50 = Color(0xFF105FCE)
val informative_60 = Color(0xFF004AB1)
val informative_70 = Color(0xFF003B8D)
val informative_80 = Color(0xFF002962)
val informative_90 = Color(0xFF001532)

val positive_1 = Color(0xFFF4FFF2)
val positive_5 = Color(0xFFE1FCDE)
val positive_10 = Color(0xFFCDF9CA)
val positive_20 = Color(0xFFA4F3A3)
val positive_30 = Color(0xFF7EEB83)
val positive_40 = Color(0xFF42D75B)
val positive_50 = Color(0xFF17BF33)
val positive_60 = Color(0xFF049B22)
val positive_70 = Color(0xFF016A1C)
val positive_80 = Color(0xFF005016)
val positive_90 = Color(0xFF00300F)

val warning_1 = Color(0xFFFFFBF2)
val warning_5 = Color(0xFFFFF2D2)
val warning_10 = Color(0xFFFFE8B1)
val warning_20 = Color(0xFFFFCF73)
val warning_30 = Color(0xFFFCB33B)
val warning_40 = Color(0xFFF2930D)
val warning_50 = Color(0xFFD66F00)
val warning_60 = Color(0xFFBA5900)
val warning_70 = Color(0xFF943C00)
val warning_80 = Color(0xFF662100)
val warning_90 = Color(0xFF340D00)

val negative_1 = Color(0xFFFFF2F3)
val negative_5 = Color(0xFFFFD4D8)
val negative_10 = Color(0xFFFFB6BC)
val negative_20 = Color(0xFFFA7D87)
val negative_30 = Color(0xFFF14A58)
val negative_40 = Color(0xFFE42131)
val negative_50 = Color(0xFFCE0718)
val negative_60 = Color(0xFFB1000F)
val negative_70 = Color(0xFF8D000C)
val negative_80 = Color(0xFF620008)
val negative_90 = Color(0xFF320004)

val transparent = Color.Transparent
val white = Color.White
val black = Color.Black

data class BentoDSColors(
    // region Brand
    val brandPrimary: Color = bento_violet_40,
    val brandSecondary: Color = bento_violet_20,
    val brandTertiary: Color = bento_violet_10,
    // endregion

    // region Background
    val bgPrimary: Color,
    val bgSecondary: Color,
    val bgOverlay: Color = neutral_20.copy(alpha = 0.4f),
    val bgInteractive: Color = bento_violet_40,
    val bgInteractiveOverlay: Color = bento_violet_10.copy(alpha = 0.4f),
    // TODO inverse
    val bgInformative: Color = informative_5,
    // TODO inverse
    val bgPositive: Color = positive_5,
    // TODO inverse
    val bgWarning: Color = warning_5,
    // TODO inverse
    val bgNegative: Color = negative_5,
    val bgScrim: Color,
    // endregion

    // region Foreground
    val fgPrimary: Color,
    val fgSecondary: Color,
    // TODO inverse fg colors
    val fgInteractive: Color = bento_violet_40,
    val fgInformative: Color = informative_40,
    val fgPositive: Color = positive_40,
    val fgWarning: Color = warning_40,
    val fgNegative: Color = negative_40,
    val fgDisabled: Color = neutral_40.copy(alpha = 0.3f),
    // endregion

    // region Text
    val textPrimary: Color,
    val textSecondary: Color,
    val textInteractive: Color = bento_violet_40,
    val textInformative: Color = informative_50,
    val textPositive: Color = positive_50,
    val textWarning: Color = warning_50,
    val textNegative: Color = negative_50,
    val textDisabled: Color = neutral_40.copy(alpha = 0.3f ),
    // endregion

    // region Interactive Link
    val linkEnabled: Color,
    val linkHover: Color,
    val linkFocus: Color,
    val linkDisabled: Color,
    // endregion

    // region Interactive Primary
    val prSolidEnabledBg: Color = bento_violet_40,
    val prSolidEnabledFg: Color = white,

    val prSolidHoverBg: Color = bento_violet_60,
    val prSolidHoverFg: Color = white,

    val prSolidFocusBg: Color = bento_violet_60,
    val prSolidFocusFg: Color = white,

    val prTransparentEnabledBg: Color = transparent,
    val prTransparentEnabledFg: Color = bento_violet_40,

    val prTransparentHoverBg: Color = bento_violet_10.copy(alpha = 0.4f),
    val prTransparentHoverFg: Color = bento_violet_50,

    val prTransparentFocusBg: Color = bento_violet_10.copy(alpha = 0.4f),
    val prTransparentFocusFg: Color = bento_violet_50,

    val prOutlineEnabled: Color = bento_violet_40,
    val prOutlineHover: Color = bento_violet_50,
    val prOutlineFocus: Color = bento_violet_50,
    // endregion

    // region Interactive Secondary
    val scSolidEnabledBg: Color = neutral_5,
    val scSolidEnabledFg: Color = neutral_90,

    val scSolidHoverBg: Color = neutral_20,
    val scSolidHoverFg: Color = neutral_90,

    val scSolidFocusBg: Color = neutral_20,
    val scSolidFocusFg: Color = neutral_90,

    val scTransparentEnabledBg: Color = transparent,
    val scTransparentEnabledFg: Color = neutral_80,

    val scTransparentHoverBg: Color = neutral_20.copy(alpha = 0.4f),
    val scTransparentHoverFg: Color = black,

    val scTransparentFocusBg: Color = neutral_20.copy(alpha = 0.4f),
    val scTransparentFocusFg: Color = black,

    val scOutlineEnabled: Color = neutral_80,
    val scOutlineHover: Color = black,
    val scOutlineFocus: Color = black,
    // endregion

    // TODO Interactive Positive
    // TODO Interactive Warning

    // region Interactive Danger
    // TODO fix
    val dngSolidEnabledBg: Color = negative_40,
    val dngSolidEnabledFg: Color = white,

    val dngSolidHoverBg: Color = negative_60,
    val dngSolidHoverFg: Color = white,

    val dngSolidFocusBg: Color = negative_60,
    val dngSolidFocusFg: Color = white,

    val dngTransparentEnabledBg: Color = transparent,
    val dngTransparentEnabledFg: Color = negative_40,

    val dngTransparentHoverBg: Color = negative_40.copy(alpha = 0.1f),
    val dngTransparentHoverFg: Color = negative_60,

    val dngTransparentFocusBg: Color = negative_40.copy(alpha = 0.1f),
    val dngTransparentFocusFg: Color = negative_60,

    val dngOutlineEnabled: Color = negative_40,
    val dngOutlineHover: Color = negative_60,
    val dngOutlineFocus: Color = negative_60,
    // endregion

    // region Interactive Disabled
    val disSolidBg: Color = neutral_20.copy(alpha = 0.2f),
    val disSolidFg: Color = neutral_40.copy(alpha = 0.3f),

    val disTransparentBg: Color = transparent,
    val disTransparentFg: Color = neutral_40.copy(alpha = 0.3f),

    val disOutline: Color = neutral_40.copy(alpha = 0.3f),

    // endregion

    // region Outline
    val outlineInteractive: Color = bento_violet_40,
    val outlineDecorative: Color = neutral_20,
    val outlineContainer: Color = neutral_5,
    val outlineInputEnabled: Color = neutral_40,
    val outlineInputHover: Color = neutral_60,
    val outlineInputFocus: Color = bento_violet_40,
    val outlineInputDisabled: Color = neutral_40.copy(alpha = 0.3f),
    val outlineInformative: Color = informative_30,
    val outlinePositive: Color = positive_50,
    val outlineWarning: Color = warning_40,
    val outlineNegative: Color = negative_30,
    // endregion
)

@Composable
fun paLightColors() = BentoDSColors(
    bgPrimary = white,
    bgSecondary = neutral_1,
    bgScrim = white.copy(alpha = 0.8f),

    fgPrimary = neutral_90,
    fgSecondary = neutral_50,

    textPrimary = neutral_90,
    textSecondary = neutral_50,

    linkEnabled = bento_violet_40,
    linkHover = bento_violet_60,
    linkFocus = bento_violet_60,
    linkDisabled = neutral_40.copy(alpha = 0.3f),
)

@Composable
fun paDarkColors() = BentoDSColors(
    bgPrimary = neutral_90,
    bgSecondary = neutral_80,
    bgScrim = neutral_90.copy(alpha = 0.6f),

    fgPrimary = neutral_1,
    fgSecondary = neutral_40,

    textPrimary = neutral_1,
    textSecondary = neutral_30,

    linkEnabled = bento_violet_10,
    linkHover = bento_violet_5,
    linkFocus = bento_violet_5,
    linkDisabled = neutral_60,
)