package io.github.iabanoubsamir.brandkit.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Describes where a logo image comes from.
 *
 * Using a sealed class lets the UI layer switch on the exact source type and
 * render accordingly, without forcing callers to work with Compose painter
 * objects at configuration time.
 */
sealed class BrandLogoSource {

    /**
     * A logo backed by an Android drawable resource.
     *
     * @property resId The resource identifier (e.g. `R.drawable.logo`).
     */
    data class DrawableResource(@param:DrawableRes val resId: Int) : BrandLogoSource()

    /**
     * A logo backed by a Compose [ImageVector] (e.g. from `Icons` or a custom SVG-to-vector).
     *
     * @property imageVector The vector to render.
     */
    data class Vector(val imageVector: ImageVector) : BrandLogoSource()

    /** Indicates that no logo has been configured; the composable renders nothing. */
    data object None : BrandLogoSource()
}

/**
 * Holds the logo configuration for a brand, including optional dark-mode variant and shape.
 *
 * @property source The logo to display in light mode (or in all modes if [darkSource] is `null`).
 * @property darkSource An optional alternative logo for dark mode. Falls back to [source] when `null`.
 * @property contentDescription Accessibility description used by screen readers.
 *   Supports [BrandText.Literal] or [BrandText.Resource] for locale support.
 *   Defaults to the brand name if not set (resolved at composition time).
 * @property shape Clip shape applied to the logo image. Defaults to [BrandLogoShape.None].
 */
data class BrandLogo(
    val source: BrandLogoSource = BrandLogoSource.None,
    val darkSource: BrandLogoSource? = null,
    val contentDescription: BrandText? = null,
    val shape: BrandLogoShape = BrandLogoShape.None,
)
