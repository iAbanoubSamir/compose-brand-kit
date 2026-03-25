package io.github.iabanoubsamir.brandkit.ui.social

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.iabanoubsamir.brandkit.R
import io.github.iabanoubsamir.brandkit.core.LocalBrandConfig
import io.github.iabanoubsamir.brandkit.model.SocialLink
import io.github.iabanoubsamir.brandkit.model.SocialPlatform
import io.github.iabanoubsamir.brandkit.ui.BrandContainer
import io.github.iabanoubsamir.brandkit.ui.BrandContainerStyle
import io.github.iabanoubsamir.brandkit.ui.preview.PreviewBrandThemeWrapper

/**
 * Renders the brand's social media links as a horizontally scrollable row of icons
 * (or icon+label chips when [showLabels] is `true`).
 *
 * Tapping an icon opens its URL in the device's default browser. When the brand has
 * no configured links this composable renders nothing.
 *
 * This composable must be called inside a
 * [io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme] wrapper.
 *
 * ### Icons-only (default)
 * ```kotlin
 * SocialLinksRow()
 * ```
 *
 * ### Icons with labels, inside a card
 * ```kotlin
 * SocialLinksRow(
 *     showLabels = true,
 *     containerStyle = BrandContainerStyle.Outlined,
 *     modifier = Modifier.fillMaxWidth(),
 * )
 * ```
 *
 * @param modifier Modifier applied to the outermost container.
 * @param showLabels When `true`, each link renders as a chip with an icon and a text label.
 *   When `false` (default), only icons are shown.
 * @param iconSize Size of each platform icon. Defaults to `24.dp`.
 * @param iconTint Tint color applied to all icons. Defaults to [MaterialTheme.colorScheme.onSurface].
 * @param containerStyle Optional container wrapping the entire row. Defaults to [BrandContainerStyle.None].
 */
@Composable
fun SocialLinksRow(
    modifier: Modifier = Modifier,
    showLabels: Boolean = false,
    iconSize: Dp = 24.dp,
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    containerStyle: BrandContainerStyle = BrandContainerStyle.None,
) {
    val brand = LocalBrandConfig.current
    val uriHandler = LocalUriHandler.current

    if (brand.socials.links.isEmpty()) return

    BrandContainer(style = containerStyle, modifier = modifier) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(
                    horizontal = if (containerStyle != BrandContainerStyle.None) 12.dp else 0.dp,
                    vertical = if (containerStyle != BrandContainerStyle.None) 8.dp else 0.dp,
                ),
            horizontalArrangement = Arrangement.spacedBy(if (showLabels) 8.dp else 0.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            brand.socials.links.forEach { link ->
                if (showLabels) {
                    SocialChip(
                        link = link,
                        iconSize = iconSize,
                        iconTint = iconTint,
                        onClick = { uriHandler.openUri(link.url) },
                    )
                } else {
                    SocialIconButton(
                        link = link,
                        iconSize = iconSize,
                        iconTint = iconTint,
                        onClick = { uriHandler.openUri(link.url) },
                    )
                }
            }
        }
    }
}

// ── Internal helpers ──────────────────────────────────────────────────────────

@Composable
private fun SocialIconButton(
    link: SocialLink,
    iconSize: Dp,
    iconTint: Color,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        SocialIcon(link = link, size = iconSize, tint = iconTint)
    }
}

@Composable
private fun SocialChip(
    link: SocialLink,
    iconSize: Dp,
    iconTint: Color,
    onClick: () -> Unit,
) {
    AssistChip(
        onClick = onClick,
        label = {
            Text(
                text = link.label,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        leadingIcon = {
            SocialIcon(link = link, size = iconSize - 4.dp, tint = iconTint)
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        border = AssistChipDefaults.assistChipBorder(
            enabled = true,
            borderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
        ),
    )
}

@Composable
private fun SocialIcon(
    link: SocialLink,
    size: Dp,
    tint: Color,
) {
    val iconModifier = Modifier.size(size)

    when {
        link.customIcon != null -> Icon(
            imageVector = link.customIcon,
            contentDescription = link.label,
            tint = tint,
            modifier = iconModifier,
        )
        else -> {
            val iconRes = link.platform.toIconRes()
            if (iconRes != null) {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = link.label,
                    tint = tint,
                    modifier = iconModifier,
                )
            } else {
                // Fallback for OTHER with no custom icon: initials badge
                Text(
                    text = link.label.take(2).uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = tint,
                    modifier = iconModifier,
                )
            }
        }
    }
}

/** Maps a [SocialPlatform] to its bundled drawable resource, or `null` if none exists. */
private fun SocialPlatform.toIconRes(): Int? = when (this) {
    SocialPlatform.TWITTER -> R.drawable.ic_social_twitter
    SocialPlatform.LINKEDIN -> R.drawable.ic_social_linkedin
    SocialPlatform.GITHUB -> R.drawable.ic_social_github
    SocialPlatform.INSTAGRAM -> R.drawable.ic_social_instagram
    SocialPlatform.FACEBOOK -> R.drawable.ic_social_facebook
    SocialPlatform.YOUTUBE -> R.drawable.ic_social_youtube
    SocialPlatform.WEBSITE -> R.drawable.ic_social_globe
    SocialPlatform.OTHER -> null
}

@Preview(showBackground = true, name = "SocialLinksRow — icons only")
@Composable
private fun SocialLinksRowPreview() {
    PreviewBrandThemeWrapper {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            SocialLinksRow()
            SocialLinksRow(showLabels = true)
            SocialLinksRow(
                showLabels = true,
                containerStyle = BrandContainerStyle.Outlined,
            )
        }
    }
}
