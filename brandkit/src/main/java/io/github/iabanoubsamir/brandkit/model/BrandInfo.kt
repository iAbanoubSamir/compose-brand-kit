package io.github.iabanoubsamir.brandkit.model

/**
 * Identifies the company or product behind a brand.
 *
 * Text fields that are user-visible ([name], [tagline], [copyrightHolder]) use [BrandText]
 * so they can be supplied as plain string literals or as Android string resource IDs for
 * full locale support.
 *
 * @property name The human-readable company or product name (e.g. "GitHub").
 * @property tagline A short slogan or mission statement displayed below the name.
 * @property website The canonical website URL (e.g. "https://github.com").
 * @property copyrightYear The year the brand was founded or the copyright started.
 * @property copyrightHolder The legal entity shown in copyright notices. Defaults to [name].
 * @property versionName The human-readable app version string (e.g. "2.1.0"). Leave blank
 *   to omit version display in composables like [io.github.iabanoubsamir.brandkit.ui.info.BrandInfoCard].
 * @property versionCode The internal integer build number. Shown in parentheses after [versionName]
 *   when both are set (e.g. "2.1.0 (42)"). Optional.
 */
data class BrandInfo(
    val name: BrandText,
    val tagline: BrandText = BrandText.Literal(""),
    val website: String = "",
    val copyrightYear: Int,
    val copyrightHolder: BrandText = name,
    val versionName: String = "",
    val versionCode: Int? = null,
) {
    /**
     * Returns a formatted version string (e.g. "2.1.0" or "2.1.0 (42)"),
     * or an empty string when [versionName] is blank.
     */
    val formattedVersion: String
        get() = when {
            versionName.isBlank() -> ""
            versionCode != null -> "$versionName ($versionCode)"
            else -> versionName
        }
}
