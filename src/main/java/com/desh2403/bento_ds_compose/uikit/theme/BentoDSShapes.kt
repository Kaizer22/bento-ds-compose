package com.desh2403.bento_ds_compose.uikit.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

private val sh8dpRC = RoundedCornerShape(8.dp)
private val sh16dpRc = RoundedCornerShape(16.dp)
private val sh24dpRc = RoundedCornerShape(24.dp)

/**
 * Corner shapes of the components. The default radii follow a hierarchy:
 * 8 dp for tiles, 16 dp for buttons and fields, 24 dp for cards, menus and sheets.
 */
data class BentoDSShapes(
    val tileShape: RoundedCornerShape = sh8dpRC,
    val inputShape: RoundedCornerShape = sh16dpRc,
    val progressBarBgShape: RoundedCornerShape = sh8dpRC,
    val progressBarFgShape: RoundedCornerShape = sh8dpRC,
    val bannerShape: RoundedCornerShape = sh16dpRc,
    val toastShape: RoundedCornerShape = sh16dpRc,
    val buttonShape: RoundedCornerShape = sh16dpRc,
    val cardShape: RoundedCornerShape = sh24dpRc,
    val menuShape: RoundedCornerShape = sh24dpRc,
    val bottomSheetModalShape: RoundedCornerShape = RoundedCornerShape(
        topStart = 24.dp,
        topEnd = 24.dp,
    ),
    val folderTabShape: RoundedCornerShape = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
    ),
    val roundShape: RoundedCornerShape = RoundedCornerShape(percent = 50),
    val noRoundedCornersShape: RoundedCornerShape = RoundedCornerShape(0.dp),
    val bottomBarShape: RoundedCornerShape = RoundedCornerShape(
        topStart = 24.dp,
        topEnd = 24.dp,
    ),
)

internal val LocalBentoDSShapes = staticCompositionLocalOf { BentoDSShapes() }