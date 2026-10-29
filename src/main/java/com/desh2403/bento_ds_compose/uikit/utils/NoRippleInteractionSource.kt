package com.desh2403.bento_ds_compose.uikit.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/** Interaction source that never emits interactions, so no ripple or pressed state is shown. */
class NoRippleInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}

/** Makes the element clickable without a ripple. */
fun Modifier.noRippleClick(onClick: () -> Unit): Modifier =
    clickable(interactionSource = null, indication = null, onClick = onClick)

/** Remembers a [NoRippleInteractionSource]. */
@Composable
fun rememberNoRippleInteractionSource() =
    remember { NoRippleInteractionSource() }