package io.github.iabanoubsamir.brandkit.ui.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.iabanoubsamir.brandkit.core.LocalBrandConfig
import io.github.iabanoubsamir.brandkit.model.BrandLogoSource
import io.github.iabanoubsamir.brandkit.model.isEmpty
import io.github.iabanoubsamir.brandkit.model.resolve
import io.github.iabanoubsamir.brandkit.ui.BrandContainer
import io.github.iabanoubsamir.brandkit.ui.BrandContainerStyle
import io.github.iabanoubsamir.brandkit.ui.copyright.CopyrightBanner
import io.github.iabanoubsamir.brandkit.ui.divider.BrandDivider
import io.github.iabanoubsamir.brandkit.ui.logo.BrandLogo
import io.github.iabanoubsamir.brandkit.ui.preview.PreviewBrandThemeWrapper
import io.github.iabanoubsamir.brandkit.ui.social.SocialLinksRow

/**
 * A highly composable "about / identity" card for displaying brand information.
 *
 * Each slot (logo, name, tagline, version, copyright, socials) can be toggled independently,
 * so you can tailor the card to fit any surface — an About screen, a settings footer,
 * an onboarding splash, or a mini identity block inside another screen.
 *
 * This composable must be called inside a
 * [io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme] wrapper.
 *
 * ### Full card
 * ```kotlin
 * BrandInfoCard(modifier = Modifier.fillMaxWidth())
 * ```
 *
 * ### Compact — name + version only, no container
 * ```kotlin
 * BrandInfoCard(
 *     showLogo = false,
 *     showTagline = false,
 *     showCopyright = false,
 *     containerStyle = BrandContainerStyle.None,
 * )
 * ```
 *
 * ### About screen card with socials
 * ```kotlin
 * BrandInfoCard(
 *     showSocials = true,
 *     showSocialLabels = true,
 *     containerStyle = BrandContainerStyle.Elevated,
 *     modifier = Modifier.fillMaxWidth(),
 * )
 * ```
 *
 * @param modifier Modifier applied to the outermost container.
 * @param showLogo Whether to display the brand logo. Auto-hides when no logo is configured.
 * @param showName Whether to display the brand name.
 * @param showTagline Whether to display the tagline. Auto-hides when tagline is blank.
 * @param showVersion Whether to display the version string. Auto-hides when version is blank.
 * @param showCopyright Whether to display the copyright banner.
 * @param showSocials Whether to display the social links row. Auto-hides when list is empty.
 * @param showSocialLabels When [showSocials] is `true`, whether to show text labels next to icons.
 * @param logoSize Size (width and height) of the logo image. Defaults to `56.dp`.
 * @param centered When `true` (default), all content is center-aligned. Set to `false` for start alignment.
 * @param containerStyle The wrapping container style. Defaults to [BrandContainerStyle.Outlined].
 */
@Composable
fun BrandInfoCard(
    modifier: Modifier = Modifier,
    showLogo: Boolean = true,
    showName: Boolean = true,
    showTagline: Boolean = true,
    showVersion: Boolean = true,
    showCopyright: Boolean = true,
    showSocials: Boolean = false,
    showSocialLabels: Boolean = false,
    logoSize: Dp = 56.dp,
    centered: Boolean = true,
    containerStyle: BrandContainerStyle = BrandContainerStyle.Outlined,
) {
    val brand = LocalBrandConfig.current
    val hasLogo = brand.logo.source !is BrandLogoSource.None
    val hasTagline = !brand.info.tagline.isEmpty()
    val hasVersion = brand.info.versionName.isNotBlank()
    val hasSocials = brand.socials.links.isNotEmpty()

    val horizontalAlignment = if (centered) Alignment.CenterHorizontally else Alignment.Start
    val textAlign = if (centered) TextAlign.Center else TextAlign.Start

    BrandContainer(style = containerStyle, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            // Logo
            if (showLogo && hasLogo) {
                BrandLogo(modifier = Modifier.size(logoSize))
            }

            // Name
            if (showName) {
                Text(
                    text = brand.info.name.resolve(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = textAlign,
                )
            }

            // Tagline
            if (showTagline && hasTagline) {
                Text(
                    text = brand.info.tagline.resolve(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = textAlign,
                )
            }

            // Version
            if (showVersion && hasVersion) {
                Text(
                    text = "Version ${brand.info.formattedVersion}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = textAlign,
                )
            }

            // Divider before copyright/socials when there's content above
            val hasDivider = (showLogo && hasLogo) || showName || (showTagline && hasTagline) || (showVersion && hasVersion)
            val hasBelow = showCopyright || (showSocials && hasSocials)
            if (hasDivider && hasBelow) {
                BrandDivider(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp))
            }

            // Copyright
            if (showCopyright) {
                CopyrightBanner()
            }

            // Socials
            if (showSocials && hasSocials) {
                SocialLinksRow(showLabels = showSocialLabels)
            }
        }
    }
}

@Preview(showBackground = true, name = "BrandInfoCard — full")
@Composable
private fun BrandInfoCardFullPreview() {
    PreviewBrandThemeWrapper {
        BrandInfoCard(
            showSocials = true,
            showSocialLabels = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
    }
}

@Preview(showBackground = true, name = "BrandInfoCard — name + version only")
@Composable
private fun BrandInfoCardMinimalPreview() {
    PreviewBrandThemeWrapper {
        BrandInfoCard(
            showLogo = false,
            showTagline = false,
            showCopyright = false,
            containerStyle = BrandContainerStyle.Surface,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview(showBackground = true, name = "BrandInfoCard — start aligned")
@Composable
private fun BrandInfoCardStartPreview() {
    PreviewBrandThemeWrapper {
        BrandInfoCard(
            centered = false,
            showSocials = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
    }
}
