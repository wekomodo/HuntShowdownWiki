plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.wekomodo.huntshowdownwiki"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.wekomodo.huntshowdownwiki"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    //GSON dependency
    implementation("com.google.code.gson:gson:2.10")
    //Retrofit Dependency
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //Interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    //noinspection BomWithoutPlatform
    androidTestImplementation("androidx.compose:compose-bom:2023.10.01")
    ksp("com.google.dagger:hilt-android-compiler:2.47")
   //coroutines
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("androidx.fragment:fragment-ktx:1.6.1")
}