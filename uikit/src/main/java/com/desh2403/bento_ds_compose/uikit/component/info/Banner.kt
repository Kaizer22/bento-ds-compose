package com.desh2403.bento_ds_compose.uikit.component.info

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.FSpace
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSButton
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSIconButton
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonType
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

// TODO Animate showing, closing
enum class BentoDSBannerType {
    INFORMATIVE, POSITIVE, WARNING, NEGATIVE, SECONDARY
}

@Composable
fun BentoDSBanner(
    modifier: Modifier = Modifier,
    bannerType: BentoDSBannerType = BentoDSBannerType.SECONDARY,
    title: String? = null,
    description: String? = null,
    @DrawableRes bannerIcon: Int? = null,
    isFillMaxWidth: Boolean = false,
    action: String? = null,
    onActionClick: (() -> Unit)? = null,
    onCloseClick: (() -> Unit)? = null,
) {
    val leftIcon = bannerIcon ?: when (bannerType) {
        BentoDSBannerType.INFORMATIVE -> R.drawable.ic_info_solid
        BentoDSBannerType.POSITIVE -> R.drawable.ic_positive_solid
        BentoDSBannerType.WARNING -> R.drawable.ic_warning_solid
        BentoDSBannerType.NEGATIVE -> R.drawable.ic_negative_solid
        BentoDSBannerType.SECONDARY -> R.drawable.ic_lightbulb_solid
    }
    val titleColor = when (bannerType) {
        BentoDSBannerType.INFORMATIVE -> BentoDSTheme.colors.text.informative
        BentoDSBannerType.POSITIVE -> BentoDSTheme.colors.text.positive
        BentoDSBannerType.WARNING -> BentoDSTheme.colors.text.warning
        BentoDSBannerType.NEGATIVE -> BentoDSTheme.colors.text.negative
        BentoDSBannerType.SECONDARY -> BentoDSTheme.colors.text.secondary
    }
    val backgroundColor = when (bannerType) {
        BentoDSBannerType.INFORMATIVE -> BentoDSTheme.colors.bg.informative
        BentoDSBannerType.POSITIVE -> BentoDSTheme.colors.bg.positive
        BentoDSBannerType.WARNING -> BentoDSTheme.colors.bg.warning
        BentoDSBannerType.NEGATIVE -> BentoDSTheme.colors.bg.negative
        BentoDSBannerType.SECONDARY -> BentoDSTheme.colors.bg.secondary
    }
    val iconColor = when (bannerType) {
        BentoDSBannerType.INFORMATIVE -> BentoDSTheme.colors.fg.informative
        BentoDSBannerType.POSITIVE -> BentoDSTheme.colors.fg.positive
        BentoDSBannerType.WARNING -> BentoDSTheme.colors.fg.warning
        BentoDSBannerType.NEGATIVE -> BentoDSTheme.colors.fg.negative
        BentoDSBannerType.SECONDARY -> BentoDSTheme.colors.fg.secondary
    }
    Column(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = BentoDSTheme.shapes.bannerShape,
            )
            .padding(
                all = BentoDSTheme.dimensions.x4,
            ),
        horizontalAlignment = Alignment.End,
    ) {
        Row(
            verticalAlignment = if (title.isNullOrEmpty() || description.isNullOrBlank())
                Alignment.CenterVertically
            else Alignment.Top
        ) {
            Icon(
                painter = painterResource(id = leftIcon),
                tint = iconColor,
                contentDescription = null,
            )
            HSpace(w = BentoDSTheme.dimensions.x4)
            Column {
                title?.let {
                    Text(
                        text = title,
                        color = titleColor,
                        style = BentoDSTheme.typography.titleMedium,
                    )
                }
                if (!title.isNullOrBlank() && !description.isNullOrBlank()) {
                    VSpace(h = BentoDSTheme.dimensions.x1)
                }
                description?.let {
                    Text(
                        text = description,
                        style = BentoDSTheme.typography.bodyMedium,
                    )
                }
            }
            HSpace(w = BentoDSTheme.dimensions.x4)
            if (isFillMaxWidth) {
                FSpace()
            }
            onCloseClick?.let {
                BentoDSIconButton(
                    size = IconSize.L,
                    buttonType = ButtonType.SECONDARY_TRANSPARENT,
                    iconRes = R.drawable.ic_x,
                    onClick = onCloseClick,
                    isNeedPadding = false,
                )
            }
        }
        if (action != null && onActionClick != null) {
            VSpace(h = BentoDSTheme.dimensions.x2)
            BentoDSButton(
                text = action,
                buttonType = ButtonType.SECONDARY_TRANSPARENT,
                onClick = onActionClick,
            )
        }
    }
}

@Preview
@Composable
fun BannerPreview() {
    BentoDSTheme {
        Column {
            BentoDSBanner(
                title = "Title",
                bannerType = BentoDSBannerType.INFORMATIVE,
                description = "Description",
                onCloseClick = {},
                action = "Action",
                onActionClick = {},
                isFillMaxWidth = true
            )
            VSpace(h = 4.dp)
            BentoDSBanner(
                title = "Title",
                description = "Description",
                onCloseClick = {},
                action = "Action",
                onActionClick = {},
                isFillMaxWidth = true
            )
            VSpace(h = 4.dp)
            BentoDSBanner(
                title = "Title",
                bannerType = BentoDSBannerType.POSITIVE,
                description = "Description",
                onCloseClick = {},
                action = "Action",
                onActionClick = {},
                isFillMaxWidth = true
            )
            VSpace(h = 4.dp)
            BentoDSBanner(
                title = "Title",
                bannerType = BentoDSBannerType.WARNING,
                description = "Description",
                onCloseClick = {},
                action = "Action",
                onActionClick = {},
                isFillMaxWidth = true
            )
            VSpace(h = 4.dp)
            BentoDSBanner(
                title = "Title",
                bannerType = BentoDSBannerType.NEGATIVE,
                description = "Description",
                onCloseClick = {},
                action = "Action",
                onActionClick = {},
                isFillMaxWidth = true
            )
            VSpace(h = 4.dp)
            BentoDSBanner(
                bannerType = BentoDSBannerType.NEGATIVE,
                description = "Description",
                onCloseClick = {},
                //action = "Action",
                //onActionClick = {},
                isFillMaxWidth = true
            )
            VSpace(h = 4.dp)
            BentoDSBanner(
                bannerType = BentoDSBannerType.NEGATIVE,
                description = "Description",
                onCloseClick = {},
                //action = "Action",
                //onActionClick = {},
                isFillMaxWidth = false
            )
            VSpace(h = 4.dp)
            BentoDSBanner(
                title = "Title",
                bannerType = BentoDSBannerType.NEGATIVE,
                //description = "Description",
                onCloseClick = {},
                //action = "Action",
                //onActionClick = {},
                isFillMaxWidth = false
            )
        }
    }
}