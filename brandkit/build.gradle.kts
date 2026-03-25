plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.vanniktech.maven.publish)
}

android {
    namespace = "io.github.iabanoubsamir.brandkit"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    coordinates("io.github.iabanoubsamir", "brandkit", "1.0.0")

    pom {
        name = "compose-brand-kit"
        description = "A Jetpack Compose library for brand identity composables — logo, footer, copyright, social links, and more."
        url = "https://github.com/iabanoubsamir/compose-brand-kit"
        licenses {
            license {
                name = "MIT License"
                url = "https://opensource.org/licenses/MIT"
            }
        }
        developers {
            developer {
                id = "iabanoubsamir"
                name = "Abanoub Samir"
                url = "https://github.com/iabanoubsamir"
            }
        }
        scm {
            url = "https://github.com/iabanoubsamir/compose-brand-kit"
            connection = "scm:git:git://github.com/iabanoubsamir/compose-brand-kit.git"
            developerConnection = "scm:git:ssh://git@github.com/iabanoubsamir/compose-brand-kit.git"
        }
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
