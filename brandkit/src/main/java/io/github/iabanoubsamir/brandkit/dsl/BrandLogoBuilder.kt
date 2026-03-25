package io.github.iabanoubsamir.brandkit.dsl

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.iabanoubsamir.brandkit.model.BrandLogo
import io.github.iabanoubsamir.brandkit.model.BrandLogoShape
import io.github.iabanoubsamir.brandkit.model.BrandLogoSource
import io.github.iabanoubsamir.brandkit.model.BrandText

/**
 * Builder for [BrandLogo]. Use inside the `logo { }` block of [BrandConfigBuilder].
 *
 * Choose one of the `from*` helpers to set the logo source; optionally call a `dark*`
 * variant to supply a separate asset for dark mode.
 *
 * ```kotlin
 * logo {
 *     fromResource(R.drawable.logo_light)
 *     darkFromResource(R.drawable.logo_dark)
 *     shape = BrandLogoShape.Circle           // or RoundedCorner(cornerPercent = 20)
 *     contentDescription = "GitHub logo"
 * }
 * ```
 */
@BrandKitDsl
class BrandLogoBuilder {

    private var source: BrandLogoSource = BrandLogoSource.None
    private var darkSource: BrandLogoSource? = null
    private var contentDescriptionText: BrandText? = null

    /** Sets the accessibility description as a plain string literal. */
    var contentDescription: String?
        get() = (contentDescriptionText as? BrandText.Literal)?.value
        set(value) {
            contentDescriptionText = value?.let { BrandText.Literal(it) }
        }

    /** Sets the accessibility description from a string resource for locale support. */
    fun contentDescriptionRes(@StringRes resId: Int) {
        contentDescriptionText = BrandText.Resource(resId)
    }

    /**
     * Clip shape applied to the logo image at render time.
     *
     * Defaults to [BrandLogoShape.None] (no clipping).
     */
    var shape: BrandLogoShape = BrandLogoShape.None

    /** Sets the logo source to a drawable resource. */
    fun fromResource(@DrawableRes resId: Int) {
        source = BrandLogoSource.DrawableResource(resId)
    }

    /** Sets the dark-mode logo source to a drawable resource. */
    fun darkFromResource(@DrawableRes resId: Int) {
        darkSource = BrandLogoSource.DrawableResource(resId)
    }

    /** Sets the logo source to a vector image. */
    fun fromVector(imageVector: ImageVector) {
        source = BrandLogoSource.Vector(imageVector)
    }

    /** Sets the dark-mode logo source to a vector image. */
    fun darkFromVector(imageVector: ImageVector) {
        darkSource = BrandLogoSource.Vector(imageVector)
    }

    internal fun build(): BrandLogo = BrandLogo(
        source = source,
        darkSource = darkSource,
        contentDescription = contentDescriptionText,
        shape = shape,
    )
}
