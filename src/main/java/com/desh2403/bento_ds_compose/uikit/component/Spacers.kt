package com.desh2403.bento_ds_compose.uikit.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Flexible space that takes all remaining width of the [androidx.compose.foundation.layout.Row]. */
@Composable
fun RowScope.FSpace() = Spacer(modifier = Modifier.weight(1f))

/** Flexible space that takes all remaining height of the [androidx.compose.foundation.layout.Column]. */
@Composable
fun ColumnScope.FSpace() = Spacer(modifier = Modifier.weight(1f))

/** Vertical gap of height [h]. */
@Composable
fun VSpace(
    h: Dp
) = Space(w = 0.dp, h = h)

/** Horizontal gap of width [w]. */
@Composable
fun HSpace(
    w: Dp
) = Space(w = w, h = 0.dp)

/** Empty box of width [w] and height [h]. */
@Composable
fun Space(
    modifier: Modifier = Modifier,
    w: Dp = 0.dp,
    h: Dp = 0.dp,
) = Spacer(modifier = modifier.size(width = w, height = h))