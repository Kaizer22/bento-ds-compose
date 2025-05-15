plugins {
    id("com.android.library")
}

android {
    namespace = "com.desh2403.bento_ds_compose.uikit"
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
//    publishing {
//        singleVariant("release") {
//            withSourcesJar()
//            withJavadocJar()
//        }
//    }
}
//publishing {
//    publications {
//        create<MavenPublication>("bento-ds-compose") {
//            groupId = "com.desh2403"
//            artifactId = "bento_ds_compose"
//            version = "0.0.1-test3"
//            afterEvaluate {
//                from(components["release"])
//            }
//        }
//    }
//}

dependencies {
    val compose = '1.6.5'
    val material3 = "1.2.1"
    val lifecycleRuntimeKtx = "2.7.0"
    val activityCompose = "1.8.2"
    val coreKtx = "1.12.0"
    val appcompat = "1.6.1"
    val junit = "4.13.2"
    val androidxTestExtJunit = "1.1.5"
    val espressoCore = "3.5.1"
    // UI
    implementation("androidx.compose.ui:ui:$compose")
    implementation("androidx.compose.ui:ui-graphics:$compose")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose")
    //implementation(libs.material)
    implementation ("androidx.compose.material3:material3:")
    implementation("com.google.accompanist:accompanist-navigation-material")
    implementation(platform("androidx.compose:compose-bom:2024.04.00"))
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleRuntimeKtx")
    implementation("androidx.activity:activity-compose:$activityCompose")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose")
    implementation("androidx.navigation:navigation-compose:$compose")

    implementation("androidx.core:core-ktx:$coreKtx")
    implementation("androidx.appcompat:appcompat:$appcompat")
    testImplementation("junit.junit:$junit")
    androidTestImplementation("androidx.test.ext:junit:$androidxTestExtJunit")
    androidTestImplementation("androidx.test.espresso:espresso-core:")
}