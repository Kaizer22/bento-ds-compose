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
            checkedThumbColor = BentoDSTheme.colors.prSolidEnabledFg,
            checkedTrackColor = BentoDSTheme.colors.prSolidEnabledBg,
            checkedBorderColor = BentoDSTheme.colors.prSolidEnabledBg,
            //checkedIconColor =,
            uncheckedThumbColor = BentoDSTheme.colors.fgInteractive,
            uncheckedTrackColor = BentoDSTheme.colors.bgPrimary,
            uncheckedBorderColor = BentoDSTheme.colors.outlineInputEnabled,
            //uncheckedIconColor =,
            disabledCheckedThumbColor = BentoDSTheme.colors.disSolidFg,
            disabledCheckedTrackColor = BentoDSTheme.colors.disSolidBg,
            disabledCheckedBorderColor = BentoDSTheme.colors.disSolidBg,
            //disabledCheckedIconColor =,
            disabledUncheckedThumbColor =BentoDSTheme.colors.fgDisabled,
            disabledUncheckedTrackColor = BentoDSTheme.colors.bgPrimary,
            disabledUncheckedBorderColor = BentoDSTheme.colors.fgDisabled,
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