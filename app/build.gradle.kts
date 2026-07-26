plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    kotlin("plugin.serialization") version "2.0.21"

    id("com.google.dagger.hilt.android") version "2.57.1"
    id("com.google.devtools.ksp")

    alias(libs.plugins.google.gms.google.services) // ✅ IMPORTANT
}

android {

    namespace = "com.example.limitlesstech.limitlessnews"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.limitlesstech.limitlessnews"
        minSdk = 33
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // ✅ Ktor (REQUIRED for io.ktor.*)
    implementation("io.ktor:ktor-client-core:2.3.7")
    implementation("io.ktor:ktor-client-cio:2.3.7")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.7")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")
    implementation("io.ktor:ktor-client-logging:2.3.7")

    // ✅ Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation("com.google.firebase:firebase-auth-ktx")


    // Navigation
    implementation("androidx.navigation:navigation-compose:2.9.3")

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Hilt
    implementation("com.google.dagger:hilt-android:2.57.1")
    implementation(libs.google.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.androidx.paging.common.android)
    ksp("com.google.dagger:hilt-compiler:2.57.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")

    // Optional
    implementation("androidx.compose.material:material-icons-extended")

    //coil
    implementation("io.coil-kt:coil-compose:2.6.0")

    //Datastore Preferences
    implementation( "androidx.datastore:datastore-preferences:1.1.7")

    //Room Database
    implementation("androidx.room:room-runtime:2.6.1")

    implementation("androidx.room:room-ktx:2.6.1")

    ksp("androidx.room:room-compiler:2.6.1")


    //Shimmer show during loading
    implementation("com.valentinilk.shimmer:compose-shimmer:1.3.3")

    // Paging 3
    implementation("androidx.paging:paging-runtime-ktx:3.3.6")

// Paging Compose
    implementation("androidx.paging:paging-compose:3.3.6")

    implementation("androidx.browser:browser:1.8.0")//open article in chrome custom tabs

// Firebase Firestore-Used to store and retrieve user data (e.g., profile name, email, bio) from Firebase Firestore.
    implementation("com.google.firebase:firebase-firestore-ktx")

// Firebase Storage-Used to upload and download files (e.g., profile images) from Firebase Storage.
    implementation("com.google.firebase:firebase-storage-ktx")

// await() support-Lets you use await() so Firebase tasks run asynchronously without callbacks, making the code simpler and cleaner.
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.8.1")

//okhttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("org.json:json:20250517")

}