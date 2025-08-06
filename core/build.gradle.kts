plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.core"
    compileSdk = libs.versions.target.sdk.version.get().toInt()

    defaultConfig {
        minSdk = libs.versions.min.sdk.version.get().toInt()

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true

    }
}

dependencies {

    // implementation module
    implementation(project(":data"))

    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.material)
    api(libs.androidx.activity)
    api(libs.androidx.constraintlayout)

    val composeBom = platform("androidx.compose:compose-bom:2025.05.00")
    api(composeBom)
    api(libs.material3)
    api(libs.compose.material)
    api(libs.compose.foundation)
    api(libs.hilt.navigation.compose)
    api(libs.compose.preview)
    api(libs.compose.ui)
    api(libs.compose.constraintlayout)
    debugApi(libs.compose.ui.tooling)

    // Jetpack
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // moshi
    api(libs.moshi)
    api(libs.moshi.adapter)
    ksp(libs.moshi.ksp)

    // Coil
    api(libs.coil.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}