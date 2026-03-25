package io.github.iabanoubsamir.brandkit.ui.logo

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import io.github.iabanoubsamir.brandkit.core.LocalBrandConfig
import io.github.iabanoubsamir.brandkit.model.BrandLogoShape
import io.github.iabanoubsamir.brandkit.model.BrandLogoSource
import io.github.iabanoubsamir.brandkit.model.resolve

/**
 * Displays the brand logo, automatically switching between light and dark variants
 * and applying the configured [BrandLogoShape] clip.
 *
 * The logo source and shape are read from [LocalBrandConfig], so this composable must
 * be called inside a [io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme] wrapper.
 * When no logo has been configured ([BrandLogoSource.None]), nothing is rendered.
 *
 * ### Usage
 * ```kotlin
 * BrandLogo(modifier = Modifier.height(48.dp))
 * ```
 *
 * @param modifier Modifier applied to the image. Use this to control size and alignment.
 * @param contentDescription Overrides the accessibility label from the brand config.
 *   Pass `null` to use the value set in the DSL (recommended).
 */
@Composable
fun BrandLogo(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    val brand = LocalBrandConfig.current
    val isDark = isSystemInDarkTheme()

    val source = if (isDark) brand.logo.darkSource ?: brand.logo.source else brand.logo.source
    val resolvedDescription = contentDescription
        ?: brand.logo.contentDescription?.resolve()
        ?: brand.info.name.resolve()

    val shapeModifier = modifier.then(
        when (val shape = brand.logo.shape) {
            BrandLogoShape.None -> Modifier
            BrandLogoShape.Circle -> Modifier.clip(CircleShape)
            is BrandLogoShape.RoundedCorner -> Modifier.clip(RoundedCornerShape(shape.cornerPercent))
        }
    )

    when (source) {
        is BrandLogoSource.DrawableResource -> {
            Image(
                painter = painterResource(source.resId),
                contentDescription = resolvedDescription,
                modifier = shapeModifier,
            )
        }
        is BrandLogoSource.Vector -> {
            Image(
                painter = rememberVectorPainter(source.imageVector),
                contentDescription = resolvedDescription,
                modifier = shapeModifier,
            )
        }
        BrandLogoSource.None -> Unit
    }
}
