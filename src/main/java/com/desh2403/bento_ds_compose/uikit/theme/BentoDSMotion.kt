package com.desh2403.bento_ds_compose.uikit.theme

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Motion tokens used by the component animations. Not part of Bento DS, which defines no motion scale.
 *
 * @param durationShort milliseconds for direct touch feedback: checking, toggling, pressing.
 * @param durationMedium milliseconds for state changes inside a screen: expanding, appearing.
 * @param durationLong milliseconds for screen transitions and sheets.
 * @param easingStandard easing for elements that stay on screen.
 * @param easingEnter easing for elements that appear.
 * @param easingExit easing for elements that disappear.
 */
data class BentoDSMotion(
    val durationShort: Int = 120,
    val durationMedium: Int = 240,
    val durationLong: Int = 400,
    val easingStandard: Easing = CubicBezierEasing(0.2f, 0f, 0f, 1f),
    val easingEnter: Easing = CubicBezierEasing(0f, 0f, 0f, 1f),
    val easingExit: Easing = CubicBezierEasing(0.3f, 0f, 1f, 1f),
)

internal val LocalBentoDSMotion = staticCompositionLocalOf { BentoDSMotion() }
