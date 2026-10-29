package com.desh2403.bento_ds_compose.uikit.component.info

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSButton
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSIconButton
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonType
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/** Status of a [BentoDSBanner]: Bento DS `kind`. */
enum class BentoDSBannerType {
    INFORMATIVE, POSITIVE, WARNING, NEGATIVE, SECONDARY
}

/**
 * Inline message with a status icon, an optional title, a description, actions and a dismiss button.
 *
 * Bento DS: [Banner](https://developer.bento-ds.com/Components/Banner)
 *
 * Extensions over Bento DS:
 * - a second action ([secondaryAction]), optionally destructive;
 * - a custom icon ([bannerIcon]);
 * - [isFillMaxWidth].
 *
 * @param modifier modifier of the banner.
 * @param bannerType status of the banner. Bento DS `kind`.
 * @param title title of the banner. Bento DS `title`.
 * @param description text of the banner. Bento DS `description`.
 * @param bannerIcon icon instead of the default status icon.
 * @param isFillMaxWidth whether the text column takes all available width.
 * @param action label of the action. Bento DS `action.label`.
 * @param onActionClick called when the action is clicked; the action is shown only together with [action].
 * Bento DS `action.onPress`.
 * @param secondaryAction label of a second action next to [action].
 * @param onSecondaryActionClick called when the second action is clicked.
 * @param isSecondaryActionDestructive shows the second action in the negative color.
 * @param onCloseClick called when the dismiss button is clicked; `null` hides the button. Bento DS `onDismiss`.
 * @param closeButtonLabel accessibility label of the dismiss button. Bento DS `dismissButtonLabel`.
 */
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
    secondaryAction: String? = null,
    onSecondaryActionClick: (() -> Unit)? = null,
    isSecondaryActionDestructive: Boolean = false,
    onCloseClick: (() -> Unit)? = null,
    closeButtonLabel: String? = null,
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
                all = BentoDSTheme.dims.x4,
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
            HSpace(w = BentoDSTheme.dims.x4)
            Column(modifier = Modifier.weight(1f, fill = isFillMaxWidth)) {
                title?.let {
                    Text(
                        text = title,
                        color = titleColor,
                        style = BentoDSTheme.typography.titleMedium,
                    )
                }
                if (!title.isNullOrBlank() && !description.isNullOrBlank()) {
                    VSpace(h = BentoDSTheme.dims.x1)
                }
                description?.let {
                    Text(
                        text = description,
                        style = BentoDSTheme.typography.bodyMedium,
                    )
                }
            }
            HSpace(w = BentoDSTheme.dims.x4)
            onCloseClick?.let {
                BentoDSIconButton(
                    size = IconSize.L,
                    buttonType = ButtonType.SECONDARY_TRANSPARENT,
                    iconRes = R.drawable.ic_x,
                    onClick = onCloseClick,
                    needPadding = false,
                    contentDescription = closeButtonLabel,
                )
            }
        }
        val hasAction = action != null && onActionClick != null
        val hasSecondaryAction = secondaryAction != null && onSecondaryActionClick != null
        if (hasAction || hasSecondaryAction) {
            VSpace(h = BentoDSTheme.dims.x2)
            Row(horizontalArrangement = Arrangement.spacedBy(BentoDSTheme.dims.x1)) {
                if (hasAction) {
                    BentoDSButton(
                        text = action,
                        buttonType = ButtonType.SECONDARY_TRANSPARENT,
                        onClick = onActionClick!!,
                    )
                }
                if (hasSecondaryAction) {
                    BentoDSButton(
                        text = secondaryAction,
                        buttonType = if (isSecondaryActionDestructive) ButtonType.NEGATIVE_TRANSPARENT
                        else ButtonType.SECONDARY_TRANSPARENT,
                        onClick = onSecondaryActionClick!!,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BannerPreview() {
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
                isFillMaxWidth = true
            )
            VSpace(h = 4.dp)
            BentoDSBanner(
                bannerType = BentoDSBannerType.NEGATIVE,
                description = "Description",
                onCloseClick = {},
                isFillMaxWidth = false
            )
            VSpace(h = 4.dp)
            BentoDSBanner(
                title = "Title",
                bannerType = BentoDSBannerType.NEGATIVE,
                onCloseClick = {},
                isFillMaxWidth = false
            )
        }
    }
}