
plugins {

    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.0.21"
    id("com.google.dagger.hilt.android") version "2.57.1"
    id("com.google.devtools.ksp")
}

android {
    buildFeatures {
        buildConfig = true

    }
    namespace = "com.example.limitlesstech.limitlessnews"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.limitlesstech.limitlessnews"
        minSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    //Navigation
    val nav_version = "2.9.3"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.crashlytics.buildtools)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

//Ktor Api call dependencies
    implementation("io.ktor:ktor-client-core:3.2.1")// Base library for making API calls
    implementation("io.ktor:ktor-client-cio:3.2.1")//  Engine that actually sends/receives request (like engine of car)(courountine based I/O)
    implementation("io.ktor:ktor-client-content-negotiation:3.2.1")//object to json
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.2.1")//json to kotlin object
    implementation("io.ktor:ktor-client-logging:3.2.1")// Shows API request/response in log (debugging)

    //Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")//Library used to parse JSON(json to kotlin object)

    //Coil for image loading
    implementation("io.coil-kt.coil3:coil-compose:3.0.3")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.3")


    // Hilt dependencies
    implementation("com.google.dagger:hilt-android:2.57.1")
    ksp("com.google.dagger:hilt-compiler:2.57.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")

}