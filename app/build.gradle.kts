plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
    id("maven-publish")
}

android {
    namespace = "com.desh2403.bento_ds_compose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.desh2403.bento_ds_compose"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Libs
    implementation(project(":uikit"))

    // Features
//    implementation(project(":bento-ds-playground"))
//    implementation(project(":task-list"))
//    implementation(project(":advanced-alarm"))
//    implementation(project(":video-collage"))

    // UI

    implementation(libs.compose.ui.ui)
    implementation(libs.compose.ui.ui.graphics)
    implementation(libs.compose.ui.ui.tooling.preview)
    // implementation(libs.material)
    implementation(libs.material3)
    implementation(libs.accompanist.navigation.material)
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.ui.tooling)
    debugImplementation(libs.compose.ui.ui.test.manifest)
    implementation(libs.androidx.navigation.compose)

    // Data
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)

    // Kotlin
    implementation(libs.core.ktx)

    // DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
//    implementation("androidx.datastore:datastore-core:1.0.0")
//    implementation("androidx.datastore:datastore-preferences-core:1.0.0")
    implementation(libs.hilt.navigation.compose)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation(libs.compose.ui.ui.test.junit4)
}