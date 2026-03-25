package io.github.iabanoubsamir.brandkit.dsl

import androidx.compose.ui.graphics.vector.ImageVector
import io.github.iabanoubsamir.brandkit.model.BrandSocials
import io.github.iabanoubsamir.brandkit.model.SocialLink
import io.github.iabanoubsamir.brandkit.model.SocialPlatform

/**
 * Builder for [BrandSocials]. Use inside the `socials { }` block of [BrandConfigBuilder].
 *
 * Add one entry per social profile using the [link] helper:
 *
 * ```kotlin
 * socials {
 *     link(SocialPlatform.GITHUB, "https://github.com")
 *     link(SocialPlatform.LINKEDIN, "https://linkedin.com/company/github")
 *     link(SocialPlatform.TWITTER, "https://twitter.com/github", label = "X / Twitter")
 *     // Custom icon override for a platform not in SocialPlatform:
 *     link(SocialPlatform.OTHER, "https://discord.gg/github", label = "Discord", icon = discordVector)
 * }
 * ```
 */
@BrandKitDsl
class BrandSocialsBuilder {

    private val links = mutableListOf<SocialLink>()

    /**
     * Adds a social media link.
     *
     * @param platform The platform this link belongs to.
     * @param url The full URL to open when tapped.
     * @param label Human-readable label shown when labels are enabled.
     *   Defaults to a title-cased platform name.
     * @param icon An optional [ImageVector] that overrides the built-in platform icon.
     *   Use this for custom platforms or to replace the default icon.
     */
    fun link(
        platform: SocialPlatform,
        url: String,
        label: String = platform.name.lowercase().replaceFirstChar { it.uppercase() },
        icon: ImageVector? = null,
    ) {
        links += SocialLink(platform = platform, url = url, label = label, customIcon = icon)
    }

    internal fun build(): BrandSocials = BrandSocials(links = links.toList())
}
