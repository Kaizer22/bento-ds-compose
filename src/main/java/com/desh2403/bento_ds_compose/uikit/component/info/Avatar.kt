package com.desh2403.bento_ds_compose.uikit.component.info

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

internal const val AVATAR_SIZE_DP = 48

sealed class AvatarData {
    data class Initial(val initials: String) : AvatarData()
    data class IconRes(@DrawableRes val icon: Int) : AvatarData()
    data class Picture(
        val pictureSource: Any,
        @DrawableRes val placeholderImage: Int,
        @DrawableRes val errorImage: Int,
    ) : AvatarData()
}

@Composable
fun Avatar(
    modifier: Modifier = Modifier
        .size(AVATAR_SIZE_DP.dp)
        .background(
            color = BentoDSTheme.colors.bg.positive,
            shape = BentoDSTheme.shapes.roundShape,
        ),
    data: AvatarData,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier.clickable(onClick = onClick),
    ) {
        when (data) {
            is AvatarData.Initial -> {
                Box(
                    modifier = modifier,
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = data.initials,
                        style = BentoDSTheme.typography.labelLarge,
                    )
                }
            }

            is AvatarData.IconRes -> {
                Box(
                    modifier = modifier,
                    contentAlignment = Alignment.Center,
                ) {
                    BentoDSIcon(
                        iconSize = IconSize.L,
                        iconRes = data.icon,
                    )
                }
            }

            is AvatarData.Picture -> {
                //TODO
            }
        }
    }
}

@Composable
@Preview
fun AvatarPreview() {
    BentoDSTheme {
        Row {
            Avatar(
                data = AvatarData.Initial("DS"),
                onClick = {}
            )
            Avatar(
                data = AvatarData.IconRes(icon = R.drawable.ic_user),
                onClick = {}
            )
        }
    }
}