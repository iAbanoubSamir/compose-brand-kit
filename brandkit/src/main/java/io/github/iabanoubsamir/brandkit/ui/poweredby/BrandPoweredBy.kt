package io.github.iabanoubsamir.brandkit.ui.poweredby

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.iabanoubsamir.brandkit.core.LocalBrandConfig
import io.github.iabanoubsamir.brandkit.model.BrandLogoSource
import io.github.iabanoubsamir.brandkit.model.resolve
import io.github.iabanoubsamir.brandkit.ui.BrandContainer
import io.github.iabanoubsamir.brandkit.ui.BrandContainerStyle
import io.github.iabanoubsamir.brandkit.ui.logo.BrandLogo
import io.github.iabanoubsamir.brandkit.ui.preview.PreviewBrandThemeWrapper

/**
 * A compact attribution badge that reads the brand name and optional logo from the
 * ambient brand config. Ideal for third-party apps or white-label products that are
 * built on top of a platform.
 *
 * Renders as: `[prefix] [logo?] [Brand Name]`
 *
 * This composable must be called inside a
 * [io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme] wrapper.
 *
 * ### Default — centered, no container
 * ```kotlin
 * BrandPoweredBy()
 * ```
 *
 * ### Custom prefix, inside a surface pill
 * ```kotlin
 * BrandPoweredBy(
 *     prefix = "Built with",
 *     containerStyle = BrandContainerStyle.Surface,
 * )
 * ```
 *
 * ### Locale-aware prefix
 * ```kotlin
 * BrandPoweredBy(prefix = stringResource(R.string.powered_by))
 * ```
 *
 * @param modifier Modifier applied to the outermost container.
 * @param prefix The label shown before the brand name. Defaults to `"Powered by"`.
 *   Pass `stringResource(R.string.powered_by)` for locale-aware text.
 * @param showLogo Whether to show the brand logo inline next to the name.
 *   Auto-hides when no logo is configured. Defaults to `true`.
 * @param logoSize Size of the inline logo. Defaults to `16.dp`.
 * @param centered When `true` (default), the badge is center-aligned. Set to `false` for start alignment.
 * @param style The [TextStyle] applied to both the prefix and brand name text.
 *   Defaults to [MaterialTheme.typography.labelSmall].
 * @param containerStyle Optional wrapping container. Defaults to [BrandContainerStyle.None].
 */
@Composable
fun BrandPoweredBy(
    modifier: Modifier = Modifier,
    prefix: String = "Powered by",
    showLogo: Boolean = true,
    logoSize: Dp = 16.dp,
    centered: Boolean = true,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    containerStyle: BrandContainerStyle = BrandContainerStyle.None,
) {
    val brand = LocalBrandConfig.current
    val hasLogo = showLogo && brand.logo.source !is BrandLogoSource.None
    val horizontalAlignment = if (centered) Alignment.CenterHorizontally else Alignment.Start

    BrandContainer(style = containerStyle, modifier = modifier) {
        Column(
            modifier = if (containerStyle != BrandContainerStyle.None) {
                Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            } else {
                Modifier
            },
            horizontalAlignment = horizontalAlignment,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = prefix,
                    style = style,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                if (hasLogo) {
                    BrandLogo(modifier = Modifier.size(logoSize))
                }
                Text(
                    text = brand.info.name.resolve(),
                    style = style,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "BrandPoweredBy — variants")
@Composable
private fun BrandPoweredByPreview() {
    PreviewBrandThemeWrapper {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            BrandPoweredBy()
            BrandPoweredBy(showLogo = false)
            BrandPoweredBy(prefix = "Built with", containerStyle = BrandContainerStyle.Surface)
            BrandPoweredBy(containerStyle = BrandContainerStyle.Outlined)
            BrandPoweredBy(centered = false)
        }
    }
}
