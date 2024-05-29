package com.desh2403.bento_ds_compose.uikit.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class BentoDSDimensions(
    val x0 : Dp = 0.dp,
    val x1 : Dp = 4.dp,
    val x2 : Dp = 8.dp,
    val x3 : Dp = 12.dp,
    val x4 : Dp = 16.dp,
    val x6 : Dp = 24.dp,
    val x8 : Dp = 32.dp,
    val x10 : Dp = 40.dp,
    val x20 : Dp = 80.dp,
    val x30 : Dp = 120.dp,
    val x40 : Dp = 160.dp,
    val x50 : Dp = 200.dp,
    val x60 : Dp = 240.dp,

    val elevationNone : Dp = x0,
    val elevationSmall : Dp = x2,
    val elevationMedium : Dp = x4,
    val elevationLarge : Dp = x8,
)

internal val LocalBentoDSDimensions = staticCompositionLocalOf { BentoDSDimensions() }