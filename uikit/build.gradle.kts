plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.desh2403.uikit"
    compileSdk = 34

    defaultConfig {
        minSdk = 28
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // UI
    implementation(libs.compose.ui.ui)
    implementation(libs.compose.ui.ui.graphics)
    implementation(libs.compose.ui.ui.tooling.preview)
    //implementation(libs.material)
    implementation (libs.material3)
    implementation(libs.accompanist.navigation.material)
    implementation(platform("androidx.compose:compose-bom:2024.04.00"))
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.ui.tooling)
    debugImplementation(libs.compose.ui.ui.test.manifest)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}