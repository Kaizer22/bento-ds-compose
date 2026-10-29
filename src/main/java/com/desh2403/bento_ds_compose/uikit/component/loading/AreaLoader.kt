package com.desh2403.bento_ds_compose.uikit.component.loading

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import kotlinx.coroutines.delay

/**
 * Area that shows its [content] and, while [isLoading], covers it with a scrim and a card with
 * an animated loader and an optional message.
 *
 * Bento DS: [AreaLoader](https://developer.bento-ds.com/Components/AreaLoader)
 *
 * Differences from Bento DS: the loader wraps the [content] instead of being placed over it by the caller.
 *
 * @param modifier modifier of the area.
 * @param isLoading shows the loader over the content.
 * @param text message under the loader. Bento DS `message`.
 * @param content content of the area.
 */
@Composable
fun BentoDSAreaLoader(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    text: String? = null,
    content: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        content.invoke()
        if (isLoading) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(color = BentoDSTheme.colors.bg.scrim),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            color = BentoDSTheme.colors.bg.primary,
                            shape = BentoDSTheme.shapes.cardShape,
                        )
                        .padding(
                            BentoDSTheme.dims.x10,
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LoaderBox()
                    VSpace(h = BentoDSTheme.dims.x6)
                    text?.let {
                        Text(
                            text = text,
                            style = BentoDSTheme.typography.bodyMedium,
                            color = BentoDSTheme.colors.text.primary,
                        )
                    }
                }

            }
        }
    }
}

private const val CIRCLE_ANIMATION_DELAY_MILLIS = 150L
private const val CIRCLE_ANIMATION_DURATION_MILLIS = 700
private const val CIRCLE_RADIUS_MAX_DP = 10
private const val CIRCLE_RADIUS_MIN_DP = 7
private const val CIRCLE_ANIMATION_AREA_WIDTH_DP = 76
private const val CIRCLE_ANIMATION_AREA_HEIGHT_DP = 60
private const val SPACE_BETWEEN_CIRCLES_DP = 8

@Composable
private fun LoaderBox() {
    Box(
        modifier = Modifier.padding(
            BentoDSTheme.dims.x10,
        )
    ) {
        val localDensity = LocalDensity.current

        val circleYAnimations = (0..2).map { index ->
            var animatedValue by remember { mutableFloatStateOf(0f) }
            LaunchedEffect(key1 = Unit) {
                delay(CIRCLE_ANIMATION_DELAY_MILLIS * index)
                animate(
                    initialValue = 0f,
                    targetValue = localDensity.run {
                        (CIRCLE_ANIMATION_AREA_HEIGHT_DP - CIRCLE_RADIUS_MAX_DP * 2).dp.toPx()
                    },
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = CIRCLE_ANIMATION_DURATION_MILLIS,
                        ),
                        repeatMode = RepeatMode.Reverse
                    )
                ) { value, _ -> animatedValue = value }
            }
            animatedValue
        }
        val circleRadiusMax = remember {
            localDensity.run {
                CIRCLE_RADIUS_MAX_DP.dp.toPx()
            }
        }
        val circleRadiusMin = remember {
            localDensity.run {
                CIRCLE_RADIUS_MIN_DP.dp.toPx()
            }
        }
        val circleSizes = (0..2).map { index ->
            var animatedValue by remember { mutableFloatStateOf(0f) }
            LaunchedEffect(key1 = Unit) {
                delay(CIRCLE_ANIMATION_DELAY_MILLIS * index)
                animate(
                    initialValue = circleRadiusMax,
                    targetValue = circleRadiusMin,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = CIRCLE_ANIMATION_DURATION_MILLIS,
                        ),
                        repeatMode = RepeatMode.Reverse
                    )
                ) { value, _ -> animatedValue = value }
            }
            animatedValue
        }
        val firstCircleColor = BentoDSTheme.colors.brand.primary
        val secondCircleColor = BentoDSTheme.colors.brand.secondary
        val thirdCircleColor = BentoDSTheme.colors.brand.tertiary

        val secondCirclePositionX = remember { CIRCLE_RADIUS_MAX_DP * 3 + SPACE_BETWEEN_CIRCLES_DP }
        val thirdCirclePositionX =
            remember { CIRCLE_RADIUS_MAX_DP * 5 + SPACE_BETWEEN_CIRCLES_DP * 2 }

        Canvas(
            modifier = Modifier.size(
                width = CIRCLE_ANIMATION_AREA_WIDTH_DP.dp,
                height = CIRCLE_ANIMATION_AREA_HEIGHT_DP.dp,
            )
        ) {
            drawCircle(
                color = firstCircleColor,
                radius = circleSizes[0],
                center = Offset(
                    x = CIRCLE_RADIUS_MAX_DP.dp.toPx(),
                    y = CIRCLE_RADIUS_MAX_DP.dp.toPx() + circleYAnimations[0],
                )
            )
            drawCircle(
                color = secondCircleColor,
                radius = circleSizes[1],
                center = Offset(
                    x = secondCirclePositionX.dp.toPx(),
                    y = CIRCLE_RADIUS_MAX_DP.dp.toPx() + circleYAnimations[1],
                )
            )
            drawCircle(
                color = thirdCircleColor,
                radius = circleSizes[2],
                center = Offset(
                    x = thirdCirclePositionX.dp.toPx(),
                    y = CIRCLE_RADIUS_MAX_DP.dp.toPx() + circleYAnimations[2],
                )
            )
        }
    }
}
