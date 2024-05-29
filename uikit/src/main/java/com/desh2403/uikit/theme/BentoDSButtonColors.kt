package com.desh2403.uikit.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class PAButtonPalette(
    val enabledBg: Color,
    val enabledFg: Color,
    val hoverBg: Color,
    val hoverFg: Color,
    val focusBg: Color,
    val focusFg: Color,
    val disabledBg: Color,
    val disabledFg: Color,
)
data class BentoDSButtonColors(
    val primarySolidButton: PAButtonPalette,
    val primaryOutlinedButton: PAButtonPalette,
    val primaryTransparentButton: PAButtonPalette,

    val secondarySolidButton: PAButtonPalette,
    val secondaryOutlinedButton: PAButtonPalette,
    val secondaryTransparentButton: PAButtonPalette,

    val dangerSolidButton: PAButtonPalette,
    val dangerOutlinedButton: PAButtonPalette,
    val dangerTransparentButton: PAButtonPalette,

    // val warningButtons
    // val positiveButtons
)

// TODO fix
@Composable
fun paButtonColors() =
    BentoDSButtonColors(
        primarySolidButton = PAButtonPalette(
            enabledBg = BentoDSTheme.colors.prSolidEnabledBg,
            enabledFg = BentoDSTheme.colors.prSolidEnabledFg,
            hoverBg = BentoDSTheme.colors.prSolidHoverBg,
            hoverFg = BentoDSTheme.colors.prSolidHoverFg,
            focusBg = BentoDSTheme.colors.prSolidFocusBg,
            focusFg = BentoDSTheme.colors.prSolidFocusFg,
            disabledBg = BentoDSTheme.colors.disSolidBg,
            disabledFg = BentoDSTheme.colors.disSolidFg,
        ),
        primaryOutlinedButton = PAButtonPalette(
            enabledBg = BentoDSTheme.colors.prTransparentEnabledBg,
            enabledFg = BentoDSTheme.colors.prOutlineEnabled,
            hoverBg = BentoDSTheme.colors.prTransparentHoverBg,
            hoverFg = BentoDSTheme.colors.prOutlineHover,
            focusBg = BentoDSTheme.colors.prTransparentFocusBg,
            focusFg = BentoDSTheme.colors.prOutlineFocus,
            disabledBg = BentoDSTheme.colors.disTransparentBg,
            disabledFg = BentoDSTheme.colors.disOutline,
        ),
        primaryTransparentButton = PAButtonPalette(
            enabledBg = BentoDSTheme.colors.prTransparentEnabledBg,
            enabledFg = BentoDSTheme.colors.prTransparentEnabledFg,
            hoverBg = BentoDSTheme.colors.prTransparentHoverBg,
            hoverFg = BentoDSTheme.colors.prTransparentHoverFg,
            focusBg = BentoDSTheme.colors.prTransparentFocusBg,
            focusFg = BentoDSTheme.colors.prTransparentFocusFg,
            disabledBg = BentoDSTheme.colors.disTransparentBg,
            disabledFg = BentoDSTheme.colors.disTransparentFg,
        ),

        secondaryOutlinedButton = PAButtonPalette(
            enabledBg = BentoDSTheme.colors.scTransparentEnabledBg,
            enabledFg = BentoDSTheme.colors.scOutlineEnabled,
            hoverBg = BentoDSTheme.colors.scTransparentHoverBg,
            hoverFg = BentoDSTheme.colors.scOutlineHover,
            focusBg = BentoDSTheme.colors.scTransparentFocusBg,
            focusFg = BentoDSTheme.colors.scOutlineFocus,
            disabledBg = BentoDSTheme.colors.disTransparentBg,
            disabledFg = BentoDSTheme.colors.disOutline,
        ),
        secondarySolidButton = PAButtonPalette(
            enabledBg = BentoDSTheme.colors.scSolidEnabledBg,
            enabledFg = BentoDSTheme.colors.scSolidEnabledFg,
            hoverBg = BentoDSTheme.colors.scSolidHoverBg,
            hoverFg = BentoDSTheme.colors.scSolidHoverFg,
            focusBg = BentoDSTheme.colors.scSolidFocusBg,
            focusFg = BentoDSTheme.colors.scSolidFocusFg,
            disabledBg = BentoDSTheme.colors.disSolidBg,
            disabledFg = BentoDSTheme.colors.disSolidFg,
        ),
        secondaryTransparentButton = PAButtonPalette(
            enabledBg = BentoDSTheme.colors.scTransparentEnabledBg,
            enabledFg = BentoDSTheme.colors.scTransparentEnabledFg,
            hoverBg = BentoDSTheme.colors.scTransparentHoverBg,
            hoverFg = BentoDSTheme.colors.scTransparentHoverFg,
            focusBg = BentoDSTheme.colors.scTransparentFocusBg,
            focusFg = BentoDSTheme.colors.scTransparentFocusFg,
            disabledBg = BentoDSTheme.colors.disTransparentBg,
            disabledFg = BentoDSTheme.colors.disTransparentFg,
        ),

        dangerOutlinedButton = PAButtonPalette(
            enabledBg = BentoDSTheme.colors.dngTransparentEnabledBg,
            enabledFg = BentoDSTheme.colors.dngOutlineEnabled,
            hoverBg = BentoDSTheme.colors.dngTransparentHoverBg,
            hoverFg = BentoDSTheme.colors.dngOutlineHover,
            focusBg = BentoDSTheme.colors.dngTransparentFocusBg,
            focusFg = BentoDSTheme.colors.dngOutlineFocus,
            disabledBg = BentoDSTheme.colors.disTransparentBg,
            disabledFg = BentoDSTheme.colors.disOutline,
        ),
        dangerSolidButton = PAButtonPalette(
            enabledBg = BentoDSTheme.colors.dngSolidEnabledBg,
            enabledFg = BentoDSTheme.colors.dngSolidEnabledFg,
            hoverBg = BentoDSTheme.colors.dngSolidHoverBg,
            hoverFg = BentoDSTheme.colors.dngSolidHoverFg,
            focusBg = BentoDSTheme.colors.dngSolidFocusBg,
            focusFg = BentoDSTheme.colors.dngSolidFocusFg,
            disabledBg = BentoDSTheme.colors.disSolidBg,
            disabledFg = BentoDSTheme.colors.disSolidFg,
        ),
        dangerTransparentButton = PAButtonPalette(
            enabledBg = BentoDSTheme.colors.dngTransparentEnabledBg,
            enabledFg = BentoDSTheme.colors.dngTransparentEnabledFg,
            hoverBg = BentoDSTheme.colors.dngTransparentHoverBg,
            hoverFg = BentoDSTheme.colors.dngTransparentHoverFg,
            focusBg = BentoDSTheme.colors.dngTransparentFocusBg,
            focusFg = BentoDSTheme.colors.dngTransparentFocusFg,
            disabledBg = BentoDSTheme.colors.disTransparentBg,
            disabledFg = BentoDSTheme.colors.disTransparentFg,
        ),
    )