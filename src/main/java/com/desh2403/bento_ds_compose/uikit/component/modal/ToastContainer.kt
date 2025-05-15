package com.desh2403.bento_ds_compose.uikit.component.modal

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable


@Composable
fun ToastContainer(
    content: @Composable () -> Unit,
) {
    Box {
        content.invoke()
    }
}