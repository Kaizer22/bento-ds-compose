package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

private val RADIO_SIZE = 24.dp
private val RADIO_BORDER = 1.5.dp

private val RADIO_SELECTED_BORDER = 7.dp

/**
 * Radio button. The selected state is a thick ring with a dot of the background color in the middle.
 *
 * Bento DS: [RadioGroupField](https://developer.bento-ds.com/Components/RadioGroupField). Bento DS has
 * only the whole group; this is a single button, so any layout can arrange the options and their labels.
 *
 * @param modifier modifier of the button.
 * @param selected whether the option is selected.
 * @param enabled whether the button reacts to clicks. Bento DS `disabled`, inverted.
 * @param onClick called when the button is clicked; `null` makes the button non-interactive,
 * e.g. when a parent row or card handles the selection.
 */
@Composable
fun BentoDSRadio(
    modifier: Modifier = Modifier,
    selected: Boolean,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    val borderWidth by animateDpAsState(
        targetValue = if (selected) RADIO_SELECTED_BORDER else RADIO_BORDER,
        animationSpec = tween(
            durationMillis = BentoDSTheme.motion.durationShort,
            easing = BentoDSTheme.motion.easingStandard,
        ),
        label = "radioBorder",
    )
    val borderColor = when {
        !enabled -> BentoDSTheme.colors.outline.inputDisabled
        selected -> BentoDSTheme.colors.outline.interactive
        else -> BentoDSTheme.colors.outline.inputEnabled
    }
    val selectableModifier = if (onClick != null) {
        Modifier.selectable(
            selected = selected,
            enabled = enabled,
            role = Role.RadioButton,
            onClick = onClick,
        )
    } else Modifier
    Box(
        modifier = modifier
            .then(selectableModifier)
            .size(RADIO_SIZE)
            .background(
                color = BentoDSTheme.colors.bg.elevated,
                shape = BentoDSTheme.shapes.roundShape,
            )
            .border(
                width = borderWidth,
                color = borderColor,
                shape = BentoDSTheme.shapes.roundShape,
            ),
    )
}

@Preview(showBackground = true)
@Composable
private fun BentoDSRadioPreview() {
    BentoDSTheme(darkMode = false) {
        Row(modifier = Modifier.padding(BentoDSTheme.dims.x4)) {
            BentoDSRadio(selected = false)
            BentoDSRadio(
                modifier = Modifier.padding(start = BentoDSTheme.dims.x4),
                selected = true,
            )
            BentoDSRadio(
                modifier = Modifier.padding(start = BentoDSTheme.dims.x4),
                selected = false,
                enabled = false,
            )
        }
    }
}
