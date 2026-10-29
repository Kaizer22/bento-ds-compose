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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

internal const val AVATAR_SIZE_DP = 48

/** Content of an [Avatar]: initials or an icon. */
sealed class AvatarData {
    /** Initials of the person, e.g. `DS`. */
    data class Initial(val initials: String) : AvatarData()

    /** Icon drawable shown instead of initials. */
    data class IconRes(@DrawableRes val icon: Int) : AvatarData()
}

/**
 * Round avatar with initials or an icon.
 *
 * Bento DS: [Avatar](https://developer.bento-ds.com/Components/Avatar)
 *
 * Differences from Bento DS:
 * - [data] accepts initials or an icon, while Bento DS derives initials from `name`;
 * - [color] is any color instead of the fixed Bento DS palette;
 * - [onClick] and [size] are extensions.
 *
 * @param modifier modifier of the avatar.
 * @param data initials or icon. Bento DS `name`.
 * @param color background of the avatar. Bento DS `color`.
 * @param size diameter of the avatar.
 * @param onClick called when the avatar is clicked; `null` makes the avatar non-interactive.
 */
@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    data: AvatarData,
    color: Color = BentoDSTheme.colors.bg.positive,
    size: Dp = AVATAR_SIZE_DP.dp,
    onClick: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(BentoDSTheme.shapes.roundShape)
            .background(color = color)
            .then(
                if (onClick != null) Modifier.clickable(role = Role.Button, onClick = onClick)
                else Modifier
            ),
        contentAlignment = Alignment.Center,
    ) {
        when (data) {
            is AvatarData.Initial -> Text(
                text = data.initials,
                style = BentoDSTheme.typography.labelLarge,
                color = BentoDSTheme.colors.text.primary,
            )

            is AvatarData.IconRes -> BentoDSIcon(
                iconSize = IconSize.L,
                iconRes = data.icon,
            )
        }
    }
}

@Composable
@Preview
private fun AvatarPreview() {
    BentoDSTheme {
        Row {
            Avatar(
                data = AvatarData.Initial("DS"),
                onClick = {},
            )
            Avatar(
                data = AvatarData.IconRes(icon = R.drawable.ic_user),
            )
        }
    }
}
