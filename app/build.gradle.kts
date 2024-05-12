import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.detekt)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jlleitschuh.gradle.ktlint)
}

android {
    namespace = "com.leom.steamini"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.leom.steamini"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties()
        val localPropertiesFile = project.rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            properties.load(localPropertiesFile.inputStream())
        }
        val geminiApiKey = properties.getProperty("GEMINI_API_KEY") ?: ""
        val steamApiKey = properties.getProperty("STEAM_API_KEY") ?: ""
        buildConfigField("String", "GEMINI_API_KEY", "\"$geminiApiKey\"")
        buildConfigField("String", "STEAM_API_KEY", "\"$steamApiKey\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.generativeai)
    implementation(libs.gson)
    implementation(libs.okhttp)
    debugImplementation(libs.bundles.ui.tooling)
    detektPlugins(libs.compose.rules)
}
