package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

private val CHECKBOX_SIZE = 24.dp
private val CHECKBOX_RADIUS = 8.dp
private val CHECKBOX_BORDER = 1.5.dp
private val CHECK_STROKE = 2.25.dp

// Bento check mark in a 24 x 24 box: (7; 12.25) -> (10.5; 15.75) -> (17; 8.25)
private const val CHECK_START_X = 7f / 24f
private const val CHECK_START_Y = 12.25f / 24f
private const val CHECK_MID_X = 10.5f / 24f
private const val CHECK_MID_Y = 15.75f / 24f
private const val CHECK_END_X = 17f / 24f
private const val CHECK_END_Y = 8.25f / 24f
private const val MIXED_INSET = 7f / 24f

private const val CHECK_DRAW_DELAY_MS = 60
private const val CHECK_DRAW_MS = 200
private const val UNCHECK_MS = 150

/**
 * Checkbox with an optional label and error message. Checking draws the check mark stroke by stroke,
 * unchecking erases it.
 *
 * Bento DS: [Checkbox](https://developer.bento-ds.com/Components/Checkbox),
 * [CheckboxField](https://developer.bento-ds.com/Components/CheckboxField)
 *
 * Extensions over Bento DS: the [mixed] state, [checkedColor] and a non-interactive mode
 * with `null` [onCheckedChange].
 *
 * @param checked whether the checkbox is checked. Bento DS `value`.
 * @param onCheckedChange called with the new state; `null` makes the checkbox non-interactive,
 * e.g. when a parent row handles the click. Bento DS `onChange`.
 * @param modifier modifier of the checkbox.
 * @param label text next to the box. Bento DS CheckboxField `label`.
 * @param mixed partial selection: a dash instead of the check mark.
 * @param enabled whether the checkbox reacts to clicks. Bento DS `disabled`, inverted.
 * @param errorMessage message under the checkbox; the border turns negative. Bento DS CheckboxField `issues`.
 * @param checkedColor fill of a checked box; the primary interactive color by default.
 */
@Composable
fun BentoDSCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    label: String? = null,
    mixed: Boolean = false,
    enabled: Boolean = true,
    errorMessage: String? = null,
    checkedColor: Color = BentoDSTheme.colors.primary.solidEnabledBg,
) {
    val toggleModifier = if (onCheckedChange != null) {
        Modifier.triStateToggleable(
            state = when {
                mixed -> ToggleableState.Indeterminate
                checked -> ToggleableState.On
                else -> ToggleableState.Off
            },
            enabled = enabled,
            role = Role.Checkbox,
            onClick = { onCheckedChange(!checked) },
        )
    } else Modifier
    Column(
        modifier = modifier.then(toggleModifier),
        verticalArrangement = Arrangement.spacedBy(BentoDSTheme.dims.x1),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CheckboxBox(
                checked = checked,
                mixed = mixed,
                enabled = enabled,
                isError = errorMessage != null,
                checkedColor = checkedColor,
            )
            if (label != null) {
                Text(
                    modifier = Modifier.padding(start = BentoDSTheme.dims.x2),
                    text = label,
                    style = BentoDSTheme.typography.bodyLarge,
                    color = if (enabled) BentoDSTheme.colors.text.primary
                    else BentoDSTheme.colors.text.disabled,
                )
            }
        }
        if (errorMessage != null) {
            Row(
                modifier = Modifier.padding(start = BentoDSTheme.dims.x4),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.size(BentoDSTheme.dims.x4),
                    painter = painterResource(R.drawable.ic_negative_solid),
                    tint = BentoDSTheme.colors.fg.negative,
                    contentDescription = null,
                )
                Text(
                    modifier = Modifier.padding(start = BentoDSTheme.dims.x1),
                    text = errorMessage,
                    style = BentoDSTheme.typography.bodySmall,
                    color = BentoDSTheme.colors.text.negative,
                )
            }
        }
    }
}

@Composable
private fun CheckboxBox(
    checked: Boolean,
    mixed: Boolean,
    enabled: Boolean,
    isError: Boolean,
    checkedColor: Color,
) {
    val isOn = checked || mixed
    val motion = BentoDSTheme.motion
    val fill by animateColorAsState(
        targetValue = when {
            !isOn -> BentoDSTheme.colors.bg.elevated
            !enabled -> BentoDSTheme.colors.disabled.solidBg
            else -> checkedColor
        },
        animationSpec = tween(motion.durationShort, easing = motion.easingStandard),
        label = "checkboxFill",
    )
    val borderColor = when {
        !enabled -> BentoDSTheme.colors.outline.inputDisabled
        isError -> BentoDSTheme.colors.outline.negative
        else -> BentoDSTheme.colors.outline.inputEnabled
    }
    val markColor = if (enabled) BentoDSTheme.colors.primary.solidEnabledFg
    else BentoDSTheme.colors.disabled.solidFg

    val trim = remember { Animatable(if (isOn) 1f else 0f) }
    var isFirst by remember { mutableStateOf(true) }
    LaunchedEffect(isOn) {
        if (isFirst) {
            isFirst = false
            return@LaunchedEffect
        }
        if (isOn) {
            trim.snapTo(0f)
            trim.animateTo(
                targetValue = 1f,
                animationSpec = tween(CHECK_DRAW_MS, CHECK_DRAW_DELAY_MS, motion.easingEnter),
            )
        } else {
            trim.animateTo(0f, tween(UNCHECK_MS, easing = motion.easingExit))
        }
    }

    val checkPath = remember { Path() }
    val segment = remember { Path() }
    val measure = remember { PathMeasure() }
    Box(modifier = Modifier.size(CHECKBOX_SIZE)) {
        Canvas(modifier = Modifier.size(CHECKBOX_SIZE)) {
            val radius = CornerRadius(CHECKBOX_RADIUS.toPx())
            drawRoundRect(color = fill, cornerRadius = radius)
            if (!isOn) {
                val border = CHECKBOX_BORDER.toPx()
                drawRoundRect(
                    color = borderColor,
                    topLeft = Offset(border / 2, border / 2),
                    size = size.copy(width = size.width - border, height = size.height - border),
                    cornerRadius = CornerRadius(radius.x - border / 2),
                    style = Stroke(width = border),
                )
            }
            val stroke = Stroke(
                width = CHECK_STROKE.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round,
            )
            if (mixed) {
                drawLine(
                    color = markColor.copy(alpha = trim.value),
                    start = Offset(size.width * MIXED_INSET, size.height / 2),
                    end = Offset(size.width * (1 - MIXED_INSET), size.height / 2),
                    strokeWidth = stroke.width,
                    cap = StrokeCap.Round,
                )
            } else if (trim.value > 0f) {
                checkPath.reset()
                checkPath.moveTo(size.width * CHECK_START_X, size.height * CHECK_START_Y)
                checkPath.lineTo(size.width * CHECK_MID_X, size.height * CHECK_MID_Y)
                checkPath.lineTo(size.width * CHECK_END_X, size.height * CHECK_END_Y)
                measure.setPath(checkPath, false)
                segment.reset()
                measure.getSegment(0f, measure.length * trim.value, segment, true)
                drawPath(path = segment, color = markColor, style = stroke)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BentoDSCheckboxPreview() {
    BentoDSTheme(darkMode = false) {
        Column(
            modifier = Modifier.padding(BentoDSTheme.dims.x4),
            verticalArrangement = Arrangement.spacedBy(BentoDSTheme.dims.x4),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(BentoDSTheme.dims.x6)) {
                BentoDSCheckbox(checked = false, onCheckedChange = {}, label = "Label")
                BentoDSCheckbox(checked = true, onCheckedChange = {}, label = "Label")
                BentoDSCheckbox(checked = false, mixed = true, onCheckedChange = {}, label = "Label")
            }
            Row(horizontalArrangement = Arrangement.spacedBy(BentoDSTheme.dims.x6)) {
                BentoDSCheckbox(checked = false, onCheckedChange = {}, errorMessage = "Error message")
                BentoDSCheckbox(checked = true, onCheckedChange = {}, errorMessage = "Error message")
            }
            Row(horizontalArrangement = Arrangement.spacedBy(BentoDSTheme.dims.x6)) {
                BentoDSCheckbox(checked = false, enabled = false, onCheckedChange = {}, label = "Label")
                BentoDSCheckbox(checked = true, enabled = false, onCheckedChange = {}, label = "Label")
            }
        }
    }
}
