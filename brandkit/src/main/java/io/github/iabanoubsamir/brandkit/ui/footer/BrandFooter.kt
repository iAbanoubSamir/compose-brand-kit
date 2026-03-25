package io.github.iabanoubsamir.brandkit.ui.footer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.iabanoubsamir.brandkit.core.LocalBrandConfig
import io.github.iabanoubsamir.brandkit.model.BrandLogoSource
import io.github.iabanoubsamir.brandkit.ui.BrandContainer
import io.github.iabanoubsamir.brandkit.ui.BrandContainerStyle
import io.github.iabanoubsamir.brandkit.ui.copyright.CopyrightBanner
import io.github.iabanoubsamir.brandkit.ui.logo.BrandLogo
import io.github.iabanoubsamir.brandkit.ui.preview.PreviewBrandThemeWrapper
import io.github.iabanoubsamir.brandkit.ui.social.SocialLinksRow

/**
 * A self-contained brand footer combining the logo, copyright notice, and social links.
 *
 * Each section is shown only when it has content:
 * - **Logo** — hidden when no logo source is configured.
 * - **Copyright** — always shown (uses [io.github.iabanoubsamir.brandkit.model.BrandInfo]).
 * - **Socials** — hidden when the brand has no social links configured.
 *
 * This composable must be called inside a
 * [io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme] wrapper.
 *
 * ### Default footer
 * ```kotlin
 * BrandFooter(modifier = Modifier.fillMaxWidth().padding(16.dp))
 * ```
 *
 * ### Compact footer with social labels, inside a card
 * ```kotlin
 * BrandFooter(
 *     showLogo = false,
 *     showSocialLabels = true,
 *     containerStyle = BrandContainerStyle.Outlined,
 *     modifier = Modifier.fillMaxWidth(),
 * )
 * ```
 *
 * @param modifier Modifier applied to the outermost container.
 * @param showLogo Whether to include the brand logo. Defaults to `true`.
 * @param showCopyright Whether to include the copyright banner. Defaults to `true`.
 * @param showSocials Whether to include the social links row. Defaults to `true`.
 * @param showSocialLabels Whether to render social links with text labels. Defaults to `false`.
 * @param centered When `true` (default), all content is center-aligned. Set to `false` for start alignment.
 * @param containerStyle Optional wrapping container. Defaults to [BrandContainerStyle.None].
 */
@Composable
fun BrandFooter(
    modifier: Modifier = Modifier,
    showLogo: Boolean = true,
    showCopyright: Boolean = true,
    showSocials: Boolean = true,
    showSocialLabels: Boolean = false,
    centered: Boolean = true,
    containerStyle: BrandContainerStyle = BrandContainerStyle.None,
) {
    val brand = LocalBrandConfig.current
    val hasLogo = brand.logo.source !is BrandLogoSource.None
    val hasSocials = brand.socials.links.isNotEmpty()
    val horizontalAlignment = if (centered) Alignment.CenterHorizontally else Alignment.Start

    BrandContainer(style = containerStyle, modifier = modifier) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (showLogo && hasLogo) {
                BrandLogo(modifier = Modifier.height(48.dp))
            }
            if (showCopyright) {
                CopyrightBanner(centered = centered)
            }
            if (showSocials && hasSocials) {
                SocialLinksRow(showLabels = showSocialLabels)
            }
        }
    }
}

@Preview(showBackground = true, name = "BrandFooter — default")
@Composable
private fun BrandFooterPreview() {
    PreviewBrandThemeWrapper {
        BrandFooter()
    }
}

@Preview(showBackground = true, name = "BrandFooter — with labels, outlined")
@Composable
private fun BrandFooterWithLabelsPreview() {
    PreviewBrandThemeWrapper {
        BrandFooter(
            showLogo = false,
            showSocialLabels = true,
            containerStyle = BrandContainerStyle.Outlined,
        )
    }
}
