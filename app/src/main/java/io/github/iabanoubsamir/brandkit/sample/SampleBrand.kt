package io.github.iabanoubsamir.brandkit.sample

import androidx.compose.ui.graphics.Color
import io.github.iabanoubsamir.brandkit.R
import io.github.iabanoubsamir.brandkit.brandKit
import io.github.iabanoubsamir.brandkit.core.BrandConfig
import io.github.iabanoubsamir.brandkit.model.BrandLogoShape
import io.github.iabanoubsamir.brandkit.model.SocialPlatform

/**
 * The sample brand configuration used throughout this demo app.
 *
 * Demonstrates a complete compose-brand-kit setup using GitHub as the sample brand:
 * - Full company info, tagline, website, copyright, and version
 * - Complete light + dark color palettes based on GitHub's design system
 * - Social media links for GitHub's official accounts
 */
val SampleBrand: BrandConfig = brandKit {
    info {
        name = "GitHub"
        tagline = "Where the world builds software"
        website = "https://github.com"
        copyrightYear = 2008
        copyrightHolder = "GitHub, Inc."
        versionName = "1.0.0"
        versionCode = 1
    }
    colors {
        primary = Color(0xFF0969DA)
        onPrimary = Color.White
        primaryContainer = Color(0xFFDDF4FF)
        onPrimaryContainer = Color(0xFF0550AE)
        secondary = Color(0xFF238636)
        onSecondary = Color.White
        secondaryContainer = Color(0xFFDAFBE1)
        onSecondaryContainer = Color(0xFF116329)
        background = Color(0xFFFFFFFF)
        onBackground = Color(0xFF1F2328)
        surface = Color(0xFFF6F8FA)
        onSurface = Color(0xFF1F2328)
        surfaceVariant = Color(0xFFEAEEF2)
        onSurfaceVariant = Color(0xFF57606A)
        error = Color(0xFFCF222E)
        onError = Color.White
    }
    darkColors {
        primary = Color(0xFF58A6FF)
        onPrimary = Color(0xFF0D1117)
        primaryContainer = Color(0xFF0C2D6B)
        onPrimaryContainer = Color(0xFFCAE8FF)
        secondary = Color(0xFF3FB950)
        onSecondary = Color(0xFF0D1117)
        secondaryContainer = Color(0xFF0F5323)
        onSecondaryContainer = Color(0xFFAFF5B4)
        background = Color(0xFF0D1117)
        onBackground = Color(0xFFE6EDF3)
        surface = Color(0xFF161B22)
        onSurface = Color(0xFFE6EDF3)
        surfaceVariant = Color(0xFF21262D)
        onSurfaceVariant = Color(0xFF8B949E)
        error = Color(0xFFF85149)
        onError = Color(0xFF0D1117)
    }
    logo {
        fromResource(R.drawable.ic_social_github)
        shape = BrandLogoShape.RoundedCorner(cornerPercent = 22)
        contentDescription = "GitHub logo"
    }
    socials {
        link(SocialPlatform.FACEBOOK, "https://facebook.com/GitHub", "Facebook")
        link(SocialPlatform.GITHUB, "https://github.com", "GitHub")
        link(SocialPlatform.TWITTER, "https://twitter.com/github", "X / Twitter")
        link(SocialPlatform.LINKEDIN, "https://linkedin.com/company/github", "LinkedIn")
        link(SocialPlatform.INSTAGRAM, "https://instagram.com/github", "Instagram")
        link(SocialPlatform.YOUTUBE, "https://youtube.com/github", "YouTube")
        link(SocialPlatform.WEBSITE, "https://github.com", "Website")
    }
}
