plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")

}

android {
    namespace = "com.carlostorres.habitsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.carlostorres.habitsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }

    //Get day of week api 25 or lower
    coreLibraryDesugaring ("com.android.tools:desugar_jdk_libs:2.0.2")

    val time_picker = "1.1.0"
    implementation ("com.maxkeppeler.sheets-compose-dialogs:core:$time_picker")
    implementation ("com.maxkeppeler.sheets-compose-dialogs:clock:$time_picker")

    // Compose Navigation
    implementation ("androidx.navigation:navigation-compose:2.5.3")

    // Firebase
    implementation (platform("com.google.firebase:firebase-bom:31.2.2"))
    implementation ("com.google.firebase:firebase-analytics-ktx")
    implementation ("com.google.firebase:firebase-auth-ktx")
    implementation ("com.google.android.gms:play-services-auth:20.4.1")

    // Dagger Hilt
    implementation ("com.google.dagger:hilt-android:2.45")
    kapt ("com.google.dagger:hilt-compiler:2.45")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")

    // Coil
    implementation ("io.coil-kt:coil-compose:2.2.2")

    // Pager
    val accompanist_version = "0.28.0"
    implementation ("com.google.accompanist:accompanist-pager:$accompanist_version")
    implementation ("com.google.accompanist:accompanist-pager-indicators:$accompanist_version")

    // Permissions
    implementation ("com.google.accompanist:accompanist-permissions:$accompanist_version")

    // Room
    val room_version = "2.5.0"
    implementation ("androidx.room:room-ktx:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-runtime:$room_version")

    // Retrofit
    val retrofit_version = "2.9.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-moshi:$retrofit_version")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // WorkManager
    val workmanager_version = "2.8.0"
    implementation ("androidx.work:work-runtime-ktx:$workmanager_version")
    implementation ("androidx.hilt:hilt-work:1.0.0")

    // Testing
    testImplementation(libs.junit)
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    kaptAndroidTest ("com.google.dagger:hilt-android-compiler:2.45")
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation ("app.cash.turbine:turbine:0.7.0")
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.45")
    val mockk_version = "1.13.4"
    testImplementation ("io.mockk:mockk:$mockk_version")
    androidTestImplementation ("androidx.work:work-testing:$workmanager_version")

}