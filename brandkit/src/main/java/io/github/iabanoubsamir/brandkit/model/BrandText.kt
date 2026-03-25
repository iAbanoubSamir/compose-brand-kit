package io.github.iabanoubsamir.brandkit.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * Represents a localizable text value that can be supplied either as a plain string
 * literal or as an Android string resource ID.
 *
 * Use [BrandText.Literal] when the value is already resolved (e.g. a hardcoded string
 * in a demo or a value retrieved at runtime). Use [BrandText.Resource] to point to a
 * `res/values/strings.xml` entry so the text automatically adapts to the device locale.
 *
 * ### Inside the DSL
 * Both forms are supported transparently via the builder's property setters and `*Res` functions:
 * ```kotlin
 * info {
 *     name = "GitHub"                        // BrandText.Literal — always English
 *     // OR
 *     nameRes(R.string.brand_name)           // BrandText.Resource — follows device locale
 *
 *     taglineRes(R.string.brand_tagline)
 *     copyrightHolder = "GitHub, Inc."
 * }
 * ```
 *
 * ### Resolving at render time
 * Call [resolve] inside any `@Composable` to get the final [String]:
 * ```kotlin
 * Text(text = brand.info.name.resolve())
 * ```
 */
sealed class BrandText {

    /** A plain string value that is used as-is regardless of locale. */
    data class Literal(val value: String) : BrandText()

    /** An Android string resource that is resolved against the current locale at render time. */
    data class Resource(@StringRes val resId: Int) : BrandText()
}

/**
 * Returns `true` when this [BrandText] is known to be blank at definition time.
 *
 * A [BrandText.Literal] is empty when its [BrandText.Literal.value] is blank.
 * A [BrandText.Resource] is always considered non-empty (the resource is assumed to have content).
 */
fun BrandText.isEmpty(): Boolean = when (this) {
    is BrandText.Literal -> value.isBlank()
    is BrandText.Resource -> false
}

/**
 * Resolves this [BrandText] to a [String] in the current composition context.
 *
 * Must be called from a `@Composable` function.
 */
@Composable
fun BrandText.resolve(): String = when (this) {
    is BrandText.Literal -> value
    is BrandText.Resource -> stringResource(resId)
}
