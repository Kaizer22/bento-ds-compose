package com.desh2403.bento_ds_compose.uikit.component.modal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

@Composable
fun ModalBottomSheetContainer(
    title: String? = null,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(
                top = BentoDSTheme.dimensions.x10,
                start = BentoDSTheme.dimensions.x6,
                end = BentoDSTheme.dimensions.x6,
                bottom = BentoDSTheme.dimensions.x6,
            )
            //.sizeIn(minHeight = BentoDSTheme.dimensions.x60)
    ) {
        title?.let{
            Text(
                text = it,
                style = BentoDSTheme.typography.titleLarge,
                color = BentoDSTheme.colors.textPrimary,
            )
            VSpace(h = BentoDSTheme.dimensions.x6)
        }
        content.invoke()
    }
}