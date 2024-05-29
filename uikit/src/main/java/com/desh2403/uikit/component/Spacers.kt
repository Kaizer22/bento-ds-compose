package com.desh2403.uikit.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// TODO to lib
@Composable
fun RowScope.FSpace() = Spacer(modifier = Modifier.weight(1f))

@Composable
fun ColumnScope.FSpace() = Spacer(modifier = Modifier.weight(1f))

@Composable
fun VSpace(
    modifier: Modifier = Modifier,
    h: Dp
) = Space(
    modifier = modifier,
    w = 0.dp, h = h,
)

@Composable
fun HSpace(
    modifier: Modifier = Modifier,
    w: Dp
) = Space(
    modifier = modifier,
    w = w, h = 0.dp,
)

@Composable
fun Space(
    modifier: Modifier = Modifier,
    w: Dp = 0.dp,
    h: Dp = 0.dp,
) = Spacer(modifier = modifier.size(width = w, height = h))