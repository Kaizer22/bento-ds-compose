package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/**
 * "− value +" control for small integers.
 *
 * Bento DS has no stepper input; the closest one is
 * [NumberField](https://developer.bento-ds.com/Components/NumberField). The whole component is an
 * extension of this library.
 *
 * @param value current value.
 * @param onValueChange called with the decremented or incremented value.
 * @param modifier modifier of the stepper.
 * @param range allowed values; the button at the edge of the range is disabled.
 * @param enabled whether the stepper reacts to clicks.
 * @param decrementContentDescription accessibility label of the minus button.
 * @param incrementContentDescription accessibility label of the plus button.
 * @param valueText text of the value, e.g. "6 / 8"; the number itself by default.
 * @param onValueClick called when the value is clicked, e.g. to open exact input.
 */
@Composable
fun BentoDSNumberStepper(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    range: IntRange = 0..Int.MAX_VALUE,
    enabled: Boolean = true,
    decrementContentDescription: String? = null,
    incrementContentDescription: String? = null,
    valueText: String = value.toString(),
    onValueClick: (() -> Unit)? = null,
) {
    val shape = BentoDSTheme.shapes.inputShape
    Row(
        modifier = modifier
            .height(BentoDSTheme.dims.x10)
            .clip(shape)
            .background(BentoDSTheme.colors.bg.elevated, shape)
            .border(BentoDSTheme.dims.x0_25, BentoDSTheme.colors.outline.decorative, shape),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        StepperButton(
            iconRes = R.drawable.ic_minus,
            enabled = enabled && value > range.first,
            contentDescription = decrementContentDescription,
            onClick = { onValueChange((value - 1).coerceIn(range)) },
        )
        Text(
            modifier = Modifier
                .widthIn(min = BentoDSTheme.dims.x10 + BentoDSTheme.dims.x1)
                .then(
                    if (onValueClick != null) {
                        Modifier.clickable(enabled = enabled, role = Role.Button, onClick = onValueClick)
                    } else Modifier
                ),
            text = valueText,
            style = BentoDSTheme.typography.strongMedium,
            color = if (enabled) BentoDSTheme.colors.text.primary
            else BentoDSTheme.colors.text.disabled,
            textAlign = TextAlign.Center,
            maxLines = 1,
        )
        StepperButton(
            iconRes = R.drawable.ic_plus,
            enabled = enabled && value < range.last,
            contentDescription = incrementContentDescription,
            onClick = { onValueChange((value + 1).coerceIn(range)) },
        )
    }
}

@Composable
private fun StepperButton(
    @DrawableRes iconRes: Int,
    enabled: Boolean,
    contentDescription: String?,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(BentoDSTheme.dims.x10)
            .clickable(enabled = enabled, role = Role.Button, onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier.size(BentoDSTheme.dims.x4 + BentoDSTheme.dims.x0_5),
            painter = painterResource(iconRes),
            tint = if (enabled) BentoDSTheme.colors.fg.primary
            else BentoDSTheme.colors.fg.disabled,
            contentDescription = contentDescription,
        )
    }
}

@Preview
@Composable
private fun BentoDSNumberStepperPreview() {
    var value by remember { mutableIntStateOf(5) }
    BentoDSTheme {
        Column {
            BentoDSNumberStepper(value = value, onValueChange = { value = it }, range = 1..7)
            BentoDSNumberStepper(value = 1, onValueChange = {}, range = 1..7)
        }
    }
}
