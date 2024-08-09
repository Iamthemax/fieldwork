import java.util.Properties

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { localProperties.load(it) }
}
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")

}

android {
    namespace = "com.sipl.aamdarparivar"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sipl.aamdarparivar"
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
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }

        }
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
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    //Gson
    implementation (libs.gson)
    // Okhttp Interceptor
    implementation(libs.logging.interceptor)
    // Lifecycle components
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)


    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)
    kapt(libs.room.compiler)


}