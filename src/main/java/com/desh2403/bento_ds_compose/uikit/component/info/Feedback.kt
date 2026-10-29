package com.desh2403.bento_ds_compose.uikit.component.info

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSButton
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonSize
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonType
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import kotlin.math.PI
import kotlin.math.sin

/** Status of a [BentoDSFeedback]: Bento DS `status`, where [DEFAULT] means no status. */
enum class FeedbackKind { DEFAULT, POSITIVE, NEGATIVE }

/** Size of a [BentoDSFeedback]: [LARGE] for a whole screen, [MEDIUM] for an area inside a screen. */
enum class FeedbackSize { MEDIUM, LARGE }

private const val RIPPLE_CYCLE_MS = 3200
private const val RIPPLE_COUNT = 2
private const val RIPPLE_MAX_SCALE = 1.6f
private const val RIPPLE_START_ALPHA = 0.16f
private const val BOB_CYCLE_MS = 3200

/**
 * Message for empty states, successes and errors: an icon in a circle, a title, a description and an action.
 * The large size uses a headline and a solid button, the medium size a title and a small transparent button.
 *
 * Bento DS: [Feedback](https://developer.bento-ds.com/Components/Feedback)
 *
 * Extensions over Bento DS: [animated].
 *
 * @param title title of the message. Bento DS `title`.
 * @param modifier modifier of the message.
 * @param description text under the title. Bento DS `description`.
 * @param kind status of the message. Bento DS `status`.
 * @param size size of the message. Bento DS `size`.
 * @param iconRes icon in the circle; by default a placeholder, a check mark or a warning sign. Bento DS `icon`.
 * @param actionText label of the action; `null` hides the button. Bento DS `action.label`.
 * @param onActionClick called when the action is clicked. Bento DS `action.onPress`.
 * @param animated the icon fades in and gently bobs, and rings spread from the circle. The rings are drawn
 * behind the circle and do not change the size of the component.
 */
@Composable
fun BentoDSFeedback(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    kind: FeedbackKind = FeedbackKind.DEFAULT,
    size: FeedbackSize = FeedbackSize.MEDIUM,
    @DrawableRes iconRes: Int? = null,
    actionText: String? = null,
    onActionClick: () -> Unit = {},
    animated: Boolean = false,
) {
    val dims = BentoDSTheme.dims
    val isLarge = size == FeedbackSize.LARGE
    Column(
        modifier = modifier.widthIn(max = if (isLarge) 440.dp else 280.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(if (isLarge) dims.x6 else dims.x4),
    ) {
        FeedbackBadge(
            kind = kind,
            iconRes = iconRes ?: defaultIcon(kind),
            iconSize = if (isLarge) IconSize.XL else IconSize.L,
            padding = if (isLarge) dims.x4 else dims.x3,
            animated = animated,
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(if (isLarge) dims.x2 else dims.x1),
        ) {
            Text(
                text = title,
                style = if (isLarge) BentoDSTheme.typography.headlineMedium
                else BentoDSTheme.typography.titleLarge,
                color = BentoDSTheme.colors.text.primary,
                textAlign = TextAlign.Center,
            )
            description?.let {
                Text(
                    text = it,
                    style = BentoDSTheme.typography.bodyMedium,
                    color = BentoDSTheme.colors.text.primary,
                    textAlign = TextAlign.Center,
                )
            }
        }
        actionText?.let {
            BentoDSButton(
                text = it,
                buttonType = if (isLarge) ButtonType.PRIMARY_SOLID else ButtonType.PRIMARY_TRANSPARENT,
                buttonSize = if (isLarge) ButtonSize.M else ButtonSize.S,
                onClick = onActionClick,
            )
        }
    }
}

@Composable
private fun FeedbackBadge(
    kind: FeedbackKind,
    @DrawableRes iconRes: Int,
    iconSize: IconSize,
    padding: Dp,
    animated: Boolean,
) {
    val colors = BentoDSTheme.colors
    val (background, tint) = when (kind) {
        FeedbackKind.DEFAULT -> colors.bg.interactiveOverlay to colors.fg.interactive
        FeedbackKind.POSITIVE -> colors.bg.positive to colors.fg.positive
        FeedbackKind.NEGATIVE -> colors.bg.negative to colors.fg.negative
    }
    val badgeModifier = if (animated) Modifier.feedbackAnimation(tint) else Modifier
    Box(
        modifier = badgeModifier
            .background(color = background, shape = BentoDSTheme.shapes.roundShape)
            .padding(padding),
        contentAlignment = Alignment.Center,
    ) {
        BentoDSIcon(
            modifier = if (animated) Modifier.iconBob() else Modifier,
            iconSize = iconSize,
            iconTint = tint,
            iconRes = iconRes,
        )
    }
}

@Composable
private fun Modifier.feedbackAnimation(rippleColor: Color): Modifier {
    val motion = BentoDSTheme.motion
    val appear = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        appear.animateTo(1f, tween(durationMillis = motion.durationLong, easing = motion.easingEnter))
    }
    val phase by rememberInfiniteTransition(label = "feedbackRipple").animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = RIPPLE_CYCLE_MS, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "feedbackRipplePhase",
    )
    return this
        .graphicsLayer {
            val scale = 0.8f + 0.2f * appear.value
            scaleX = scale
            scaleY = scale
            alpha = appear.value
        }
        .drawBehind {
            val baseRadius = size.minDimension / 2
            repeat(RIPPLE_COUNT) { index ->
                val progress = (phase + index.toFloat() / RIPPLE_COUNT) % 1f
                val eased = 1f - (1f - progress) * (1f - progress)
                drawCircle(
                    color = rippleColor,
                    radius = baseRadius * (1f + (RIPPLE_MAX_SCALE - 1f) * eased),
                    alpha = RIPPLE_START_ALPHA * (1f - progress) * appear.value,
                )
            }
        }
}

@Composable
private fun Modifier.iconBob(): Modifier {
    val phase by rememberInfiniteTransition(label = "feedbackBob").animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = BOB_CYCLE_MS, easing = LinearEasing),
        ),
        label = "feedbackBobPhase",
    )
    val amplitude = BentoDSTheme.dims.x0_5
    return graphicsLayer {
        translationY = amplitude.toPx() * sin(2 * PI * phase).toFloat()
    }
}

@DrawableRes
private fun defaultIcon(kind: FeedbackKind): Int = when (kind) {
    FeedbackKind.DEFAULT -> R.drawable.ic_placeholder
    FeedbackKind.POSITIVE -> R.drawable.ic_positive_solid
    FeedbackKind.NEGATIVE -> R.drawable.ic_negative_solid
}

@Preview(showBackground = true, widthDp = 920, heightDp = 1028)
@Composable
private fun FeedbackPreview() {
    BentoDSTheme {
        Row(
            modifier = Modifier.padding(80.dp),
            horizontalArrangement = Arrangement.spacedBy(40.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(80.dp)) {
                FeedbackKind.entries.forEach { kind ->
                    BentoDSFeedback(
                        title = "Title",
                        description = "Description",
                        kind = kind,
                        size = FeedbackSize.LARGE,
                        actionText = "Action",
                    )
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(80.dp)) {
                FeedbackKind.entries.forEach { kind ->
                    BentoDSFeedback(
                        title = "Title",
                        description = "Description",
                        kind = kind,
                        actionText = "Action",
                        animated = true,
                    )
                }
            }
        }
    }
}
