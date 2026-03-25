package io.github.iabanoubsamir.brandkit.dsl

import androidx.annotation.StringRes
import io.github.iabanoubsamir.brandkit.model.BrandInfo
import io.github.iabanoubsamir.brandkit.model.BrandText

/**
 * Builder for [BrandInfo]. Use inside the `info { }` block of [BrandConfigBuilder].
 *
 * All localizable text fields ([name], [tagline], [copyrightHolder]) accept either a plain
 * string literal (via property assignment) or an Android string resource ID (via the `*Res`
 * functions) for full locale support.
 *
 * ### Plain strings
 * ```kotlin
 * info {
 *     name = "GitHub"
 *     tagline = "Where the world builds software"
 *     copyrightHolder = "GitHub, Inc."
 *     copyrightYear = 2008
 *     versionName = "1.0.0"
 * }
 * ```
 *
 * ### String resources (locale-aware)
 * ```kotlin
 * info {
 *     nameRes(R.string.brand_name)
 *     taglineRes(R.string.brand_tagline)
 *     copyrightHolderRes(R.string.brand_copyright_holder)
 *     copyrightYear = 2008
 * }
 * ```
 */
@BrandKitDsl
class BrandInfoBuilder {

    private var nameText: BrandText? = null
    private var taglineText: BrandText = BrandText.Literal("")
    private var copyrightHolderText: BrandText? = null

    /** Sets the brand name as a plain string literal. */
    var name: String?
        get() = (nameText as? BrandText.Literal)?.value
        set(value) {
            nameText = value?.let { BrandText.Literal(it) }
        }

    /** Sets the brand name from a string resource for locale support. */
    fun nameRes(@StringRes resId: Int) {
        nameText = BrandText.Resource(resId)
    }

    /** Sets the tagline as a plain string literal. */
    var tagline: String
        get() = (taglineText as? BrandText.Literal)?.value ?: ""
        set(value) {
            taglineText = BrandText.Literal(value)
        }

    /** Sets the tagline from a string resource for locale support. */
    fun taglineRes(@StringRes resId: Int) {
        taglineText = BrandText.Resource(resId)
    }

    /** The canonical website URL. */
    var website: String = ""

    /** The year the brand was founded or copyright started. Required. */
    var copyrightYear: Int? = null

    /** Sets the copyright holder as a plain string literal. Defaults to [name] when not set. */
    var copyrightHolder: String?
        get() = (copyrightHolderText as? BrandText.Literal)?.value
        set(value) {
            copyrightHolderText = value?.let { BrandText.Literal(it) }
        }

    /** Sets the copyright holder from a string resource for locale support. */
    fun copyrightHolderRes(@StringRes resId: Int) {
        copyrightHolderText = BrandText.Resource(resId)
    }

    /**
     * The human-readable app version string (e.g. "2.1.0").
     * Leave blank to omit version display from brand composables.
     */
    var versionName: String = ""

    /**
     * The internal integer build number.
     * When set alongside [versionName], displayed as "2.1.0 (42)".
     */
    var versionCode: Int? = null

    internal fun build(): BrandInfo {
        val resolvedName = checkNotNull(nameText) {
            "BrandInfo requires a `name`. Add `name = \"Your Brand\"` inside the `info { }` block."
        }
        val resolvedYear = checkNotNull(copyrightYear) {
            "BrandInfo requires a `copyrightYear`. Add `copyrightYear = 2021` inside the `info { }` block."
        }
        return BrandInfo(
            name = resolvedName,
            tagline = taglineText,
            website = website,
            copyrightYear = resolvedYear,
            copyrightHolder = copyrightHolderText ?: resolvedName,
            versionName = versionName,
            versionCode = versionCode,
        )
    }
}
