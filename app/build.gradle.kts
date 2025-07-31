plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.navigation.safeargs.kotlin)
    alias(libs.plugins.compose.compiler)

}

android {
    namespace = "com.example.mvi_architecture"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.mvi_architecture"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    val composeBom = platform("androidx.compose:compose-bom:2025.05.00")
    implementation(composeBom)
    implementation(libs.material3)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.compose.preview)
    implementation(libs.compose.ui)
    debugImplementation(libs.compose.ui.tooling)
    // Jetpack
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // DataStore
    implementation(libs.preferences.data.store)

    // Navigation
    implementation(libs.navigation.fragment)

    //

    //Network
    implementation(libs.retrofit)
    implementation(libs.retrofit.convert.moshi)
    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)


    // moshi
    implementation(libs.moshi)
    implementation(libs.moshi.adapter)
    ksp(libs.moshi.ksp)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}