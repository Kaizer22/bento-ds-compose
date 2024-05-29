package com.desh2403.bento_ds_compose.uikit.component.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

enum class DividerType {
    NORMAL, DECORATIVE
}
@Composable
fun BentoDSDivider(
    modifier: Modifier = Modifier,
    dividerType: DividerType,
) {
    val dividerColor = when(dividerType){
        DividerType.NORMAL -> BentoDSTheme.colors.brandSecondary
        DividerType.DECORATIVE -> BentoDSTheme.colors.outlineDecorative
    }
    val dividerThickness = when(dividerType) {
        DividerType.NORMAL -> BentoDSTheme.dimensions.x0_5
        DividerType.DECORATIVE -> BentoDSTheme.dimensions.x0_25
    }
    HorizontalDivider(
        modifier = modifier,
        thickness = dividerThickness,
        color = dividerColor
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DividerPreview() {
    BentoDSTheme {
        Column {
            VSpace(h = 8.dp)
            BentoDSDivider(
                modifier = Modifier.fillMaxWidth(),
                dividerType = DividerType.NORMAL,
            )
            VSpace(h = 8.dp)
            BentoDSDivider(
                modifier = Modifier.fillMaxWidth(),
                dividerType = DividerType.DECORATIVE,
            )
        }
    }
}