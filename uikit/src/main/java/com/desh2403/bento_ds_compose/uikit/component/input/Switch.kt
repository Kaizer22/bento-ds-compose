package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

// TODO Switch
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
            //checkedIconColor =,
            uncheckedThumbColor = BentoDSTheme.colors.fg.interactive,
            uncheckedTrackColor = BentoDSTheme.colors.bg.primary,
            uncheckedBorderColor = BentoDSTheme.colors.outline.inputEnabled,
            //uncheckedIconColor =,
            disabledCheckedThumbColor = BentoDSTheme.colors.disabled.solidFg,
            disabledCheckedTrackColor = BentoDSTheme.colors.disabled.solidBg,
            disabledCheckedBorderColor = BentoDSTheme.colors.disabled.solidBg,
            //disabledCheckedIconColor =,
            disabledUncheckedThumbColor =BentoDSTheme.colors.fg.disabled,
            disabledUncheckedTrackColor = BentoDSTheme.colors.bg.primary,
            disabledUncheckedBorderColor = BentoDSTheme.colors.fg.disabled,
            //disabledUncheckedIconColor =,
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SwitchPreview() {
    BentoDSTheme {
        Column {
            BentoDSSwitch(checked = true) {}
            BentoDSSwitch(checked = false) {}
            BentoDSSwitch(checked = true, enabled = false) {}
            BentoDSSwitch(checked = false, enabled = false) {}
        }
    }
}