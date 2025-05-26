package com.desh2403.bento_ds_compose.uikit.component.loading

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

private const val label = "inline_loader_rotation_angle"

@Composable
fun BentoDSInlineLoader(
    modifier: Modifier = Modifier,
    text: String? = null,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val transition = rememberInfiniteTransition(label = label)
        val rotationAngle by transition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1000,
                ),
                repeatMode = RepeatMode.Restart,
            ), label = label,
        )
        Image(
            painter = painterResource(id = R.drawable.ic_spinner),
            contentDescription = null,
            modifier = Modifier
                .size(BentoDSTheme.dims.x4)
                .rotate(rotationAngle)
                .aspectRatio(1f)
        )
        text?.let {
            HSpace(w = BentoDSTheme.dims.x2)
            Text(
                text = text,
                style = BentoDSTheme.typography.bodyMedium,
                color = BentoDSTheme.colors.text.secondary,
            )
        }
    }
}

@Preview
@Composable
fun InlineLoaderPreview() {
    BentoDSTheme {
        BentoDSInlineLoader(
            text = "Loading..."
        )
    }
}