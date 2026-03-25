package io.github.iabanoubsamir.brandkit.ui.version

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.iabanoubsamir.brandkit.core.LocalBrandConfig
import io.github.iabanoubsamir.brandkit.ui.BrandContainer
import io.github.iabanoubsamir.brandkit.ui.BrandContainerStyle
import io.github.iabanoubsamir.brandkit.ui.preview.PreviewBrandThemeWrapper

/**
 * A compact badge displaying the brand's version string.
 *
 * Reads [io.github.iabanoubsamir.brandkit.model.BrandInfo.versionName] (and optionally the
 * build number) from the ambient brand config. Renders nothing when no version is configured.
 *
 * This composable must be called inside a
 * [io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme] wrapper.
 *
 * ### Default badge (surface pill)
 * ```kotlin
 * BrandVersionBadge()
 * ```
 *
 * ### Plain text, no container
 * ```kotlin
 * BrandVersionBadge(containerStyle = BrandContainerStyle.None)
 * ```
 *
 * ### With build number
 * ```kotlin
 * BrandVersionBadge(showBuildNumber = true)
 * ```
 *
 * @param modifier Modifier applied to the outermost container.
 * @param showBuildNumber When `true`, appends the build number — e.g. "v1.0.0 (42)".
 *   Defaults to `false`.
 * @param style The [TextStyle] for the version text. Defaults to [MaterialTheme.typography.labelSmall].
 * @param containerStyle The wrapping container style. Defaults to [BrandContainerStyle.Surface].
 */
@Composable
fun BrandVersionBadge(
    modifier: Modifier = Modifier,
    showBuildNumber: Boolean = false,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    containerStyle: BrandContainerStyle = BrandContainerStyle.Surface,
) {
    val brand = LocalBrandConfig.current
    val version = if (showBuildNumber) brand.info.formattedVersion else brand.info.versionName

    if (version.isBlank()) return

    BrandContainer(style = containerStyle, modifier = modifier) {
        Text(
            text = "v$version",
            style = style,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = if (containerStyle != BrandContainerStyle.None) {
                Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
            } else {
                Modifier
            },
        )
    }
}

@Preview(showBackground = true, name = "BrandVersionBadge — variants")
@Composable
private fun BrandVersionBadgePreview() {
    PreviewBrandThemeWrapper {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            BrandVersionBadge()
            BrandVersionBadge(showBuildNumber = true)
            BrandVersionBadge(containerStyle = BrandContainerStyle.Outlined)
            BrandVersionBadge(containerStyle = BrandContainerStyle.None)
        }
    }
}
