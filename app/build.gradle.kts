plugins {
    id("com.android.application")
}

//apply plugin: 'com.android.application'
//apply plugin: 'com.google.gms.google-services'
//plugins {
//    id ("com.android.application")
//    id ("com.google.gms.google-services")
//}


android {
    compileSdk = 34

    namespace = "com.example.myapplication"
    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 34
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
}


dependencies {
    implementation(libs.recyclerview)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.github.bumptech.glide:glide:4.15.1")
    implementation(libs.retrofit)
    implementation(libs.retrofit2.converter.gson)
    // https://mvnrepository.com/artifact/com.github.PhilJay/MPAndroidChart
    implementation("com.github.PhilJay:MPAndroidChart:3.0.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")

}

