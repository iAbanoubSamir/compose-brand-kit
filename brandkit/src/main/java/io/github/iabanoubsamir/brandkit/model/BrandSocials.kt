package io.github.iabanoubsamir.brandkit.model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Supported social media and web platforms.
 *
 * Use [OTHER] for any platform not listed here; supply a custom [SocialLink.label] and
 * optionally a [SocialLink.customIcon] in that case.
 */
enum class SocialPlatform {
    TWITTER,
    LINKEDIN,
    GITHUB,
    INSTAGRAM,
    FACEBOOK,
    YOUTUBE,
    WEBSITE,
    OTHER,
}

/**
 * A single link to a social media profile or external page.
 *
 * @property platform The platform this link belongs to. Used to look up the default icon.
 * @property url The full URL to open when tapped (e.g. "https://github.com").
 * @property label Human-readable label shown when labels are enabled. Defaults to a
 *   title-cased platform name (e.g. "Github", "LinkedIn").
 * @property customIcon An optional [ImageVector] that overrides the bundled platform icon.
 *   Useful for platforms not listed in [SocialPlatform] or for custom branding.
 */
data class SocialLink(
    val platform: SocialPlatform,
    val url: String,
    val label: String = platform.name.lowercase().replaceFirstChar { it.uppercase() },
    val customIcon: ImageVector? = null,
)

/**
 * The collection of social links associated with a brand.
 *
 * @property links An ordered list of [SocialLink] items rendered by [SocialLinksRow].
 *   An empty list hides the social section entirely.
 */
data class BrandSocials(
    val links: List<SocialLink> = emptyList(),
)
