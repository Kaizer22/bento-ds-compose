package com.desh2403.bento_ds_compose.uikit.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

data class BentoDSShapes(
    val bannerShape: RoundedCornerShape = RoundedCornerShape(16.dp),
    val toastShape: RoundedCornerShape = RoundedCornerShape(16.dp),
    val buttonShape: RoundedCornerShape = RoundedCornerShape(16.dp),
    val cardShape: RoundedCornerShape = RoundedCornerShape(24.dp),
    val bottomSheetModalShape: RoundedCornerShape = RoundedCornerShape(
        topStart = 40.dp,
        topEnd = 40.dp,
    ),

    val roundShape: RoundedCornerShape = RoundedCornerShape(percent = 50),
    val noRoundedCornersShape: RoundedCornerShape = RoundedCornerShape(0.dp),
)

internal val LocalBentoDSShapes = staticCompositionLocalOf { BentoDSShapes() }