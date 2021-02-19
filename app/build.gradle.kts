import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")
    defaultConfig {
        applicationId = "ren.imyan.kirby"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        vectorDrawables.generatedDensities("mdpi", "hdpi", "xhdpi", "xxhdpi")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${getKotlinPluginVersion()}")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.3.0-beta01")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0")
    implementation("com.github.bumptech.glide:glide:4.11.0")
    implementation("com.github.EndureBlaze:GlideCache:1.2")
    implementation("com.oasisfeng.condom:library:2.5.0")
    implementation("androidx.activity:activity-ktx:1.2.0")
    implementation("androidx.fragment:fragment-ktx:1.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.1")
    implementation("cn.bmob.android:bmob-sdk:3.7.9")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.11.0")
    testImplementation("junit:junit:4.13.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    implementation(project(":base"))
    implementation(project(":ktx"))
    implementation(project(":util"))
    implementation(project(":theme"))
}