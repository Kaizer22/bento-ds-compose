package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/**
 * On/off switch in Bento DS colors.
 *
 * Bento DS: [Switch](https://developer.bento-ds.com/Components/Switch)
 *
 * Differences from Bento DS: no built-in label; put the switch into a row or a
 * [com.desh2403.bento_ds_compose.uikit.component.info.BentoDSListItem].
 *
 * @param modifier modifier of the switch.
 * @param checked whether the switch is on. Bento DS `value`.
 * @param enabled whether the switch reacts to clicks. Bento DS `disabled`, inverted.
 * @param onCheckedChange called with the new state. Bento DS `onChange`.
 */
@Composable
fun BentoDSSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit,
) {
    Switch(
        modifier = modifier,
        checked = checked,
        enabled = enabled,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = BentoDSTheme.colors.primary.solidEnabledFg,
            checkedTrackColor = BentoDSTheme.colors.primary.solidEnabledBg,
            checkedBorderColor = BentoDSTheme.colors.primary.solidEnabledBg,
            uncheckedThumbColor = BentoDSTheme.colors.fg.interactive,
            uncheckedTrackColor = BentoDSTheme.colors.bg.primary,
            uncheckedBorderColor = BentoDSTheme.colors.outline.inputEnabled,
            disabledCheckedThumbColor = BentoDSTheme.colors.disabled.solidFg,
            disabledCheckedTrackColor = BentoDSTheme.colors.disabled.solidBg,
            disabledCheckedBorderColor = BentoDSTheme.colors.disabled.solidBg,
            disabledUncheckedThumbColor = BentoDSTheme.colors.fg.disabled,
            disabledUncheckedTrackColor = BentoDSTheme.colors.bg.primary,
            disabledUncheckedBorderColor = BentoDSTheme.colors.fg.disabled,
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SwitchPreview() {
    BentoDSTheme {
        Column {
            BentoDSSwitch(checked = true) {}
            BentoDSSwitch(checked = false) {}
            BentoDSSwitch(checked = true, enabled = false) {}
            BentoDSSwitch(checked = false, enabled = false) {}
        }
    }
}