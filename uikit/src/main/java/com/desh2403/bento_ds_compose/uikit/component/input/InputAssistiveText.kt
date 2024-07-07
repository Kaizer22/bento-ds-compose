package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

@Composable
fun InputAssistiveText(
    assistiveText: String?,
    errorIconTint: Color,
    isError: Boolean,
) {
    if (isError || assistiveText != null) {
        VSpace(h = BentoDSTheme.dimensions.x1)
        Row {
            HSpace(w = BentoDSTheme.dimensions.x4)
            if (isError) {
                Icon(
                    modifier = Modifier.size(
                        BentoDSTheme.dimensions.x4
                    ),
                    tint = errorIconTint,
                    painter = painterResource(id = R.drawable.ic_negative_solid),
                    contentDescription = null,
                )
                HSpace(w = BentoDSTheme.dimensions.x1)
            }
            Text(
                text = assistiveText.orEmpty(),
                style = BentoDSTheme.typography.bodySmall,
                color = if (isError) BentoDSTheme.colors.textNegative
                else BentoDSTheme.colors.textSecondary
            )
        }
    }
}