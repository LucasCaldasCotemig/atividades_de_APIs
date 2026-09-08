plugins {
id("com.android.application")
id("org.jetbrains.kotlin.android")
}

android {
namespace = "com.lucao.rickandmorty"
compileSdk = 34

defaultConfig {
applicationId = "com.lucao.rickandmorty"
minSdk = 24
targetSdk = 34
versionCode = 1
versionName = "1.0"
}

buildTypes {
release {
isMinifyEnabled = false
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
kotlinCompilerExtensionVersion = "1.5.14"
}
}

dependencies {
implementation(platform("androidx.compose:compose-bom:2024.06.00"))
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.ui:ui-tooling-preview")
implementation("androidx.compose.material3:material3")
implementation("androidx.activity:activity-compose:1.9.0")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.2")
implementation("androidx.navigation:navigation-compose:2.7.7")

implementation("com.squareup.retrofit2:retrofit:2.11.0")
implementation("com.squareup.retrofit2:converter-gson:2.11.0")
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

implementation("io.coil-kt:coil-compose:2.6.0")

debugImplementation("androidx.compose.ui:ui-tooling")
}
