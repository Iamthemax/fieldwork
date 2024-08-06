import java.util.Properties

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { localProperties.load(it) }
}
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.fieldwork"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.fieldwork"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildFeatures.buildConfig=true
        buildConfigField("String", "API_URL", "\"${localProperties.getProperty("API_URL", "")}\"")
        val defaultReadTimeout = "30L" // Default value for read timeout in seconds
        val readTimeout = localProperties.getProperty("READ_TIMEOUT", defaultReadTimeout)
        buildConfigField("Long", "READ_TIMEOUT", readTimeout)
        val defaultConnectTimeout = "30L" // Default value for connect timeout in seconds
        val connectTimeout = localProperties.getProperty("CONNECT_TIMEOUT", defaultConnectTimeout)
        buildConfigField("Long", "CONNECT_TIMEOUT", connectTimeout)

    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures{
        viewBinding=true
        buildConfig=true
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

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    //Gson
    implementation ("com.google.code.gson:gson:2.11.0")
    // Okhttp Interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4")
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")


}