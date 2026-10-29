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

/** Divider style: [NORMAL] is a neutral line, [DECORATIVE] is a thin line in the brand color. */
enum class DividerType {
    NORMAL, DECORATIVE
}

/**
 * Horizontal line that separates content.
 *
 * Bento DS: [Divider](https://developer.bento-ds.com/Components/Divider)
 *
 * Differences from Bento DS: only the horizontal orientation is supported; [dividerType] is an extension.
 *
 * @param modifier modifier of the divider.
 * @param dividerType style of the divider.
 */
@Composable
fun BentoDSDivider(
    modifier: Modifier = Modifier,
    dividerType: DividerType,
) {
    val dividerColor = when (dividerType) {
        DividerType.NORMAL -> BentoDSTheme.colors.outline.decorative
        DividerType.DECORATIVE -> BentoDSTheme.colors.brand.secondary
    }
    val dividerThickness = when (dividerType) {
        DividerType.NORMAL -> BentoDSTheme.dims.x0_5
        DividerType.DECORATIVE -> BentoDSTheme.dims.x0_25
    }
    HorizontalDivider(
        modifier = modifier,
        thickness = dividerThickness,
        color = dividerColor
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DividerPreview() {
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