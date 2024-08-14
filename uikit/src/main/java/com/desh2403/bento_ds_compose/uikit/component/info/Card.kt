package com.desh2403.bento_ds_compose.uikit.component.info

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

@Composable
fun BentoDSCard(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(BentoDSTheme.dimensions.x6),
    color: Color = BentoDSTheme.colors.bg.primary,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Box(modifier = Modifier.padding(BentoDSTheme.dimensions.x3)) {
        Card(
            modifier = modifier,
            shape = BentoDSTheme.shapes.cardShape,
            elevation = CardDefaults.cardElevation(
                defaultElevation = BentoDSTheme.dimensions.elevationSmall
            ),
            onClick = onClick ?: {},
            colors = CardDefaults.cardColors(
                containerColor = color,
            )
        ) {
            Box(modifier = Modifier.padding(paddingValues)) {
                content.invoke()
            }
        }
    }
}