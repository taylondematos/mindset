plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")

}

android {
    namespace = "com.catshouse.sabedoriadiaria2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.catshouse.sabedoriadiaria2"
        minSdk = 28
        targetSdk = 34
        versionCode = 62
        versionName = "10.0"

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
        kotlinCompilerExtensionVersion = "1.5.1"
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
    implementation(libs.core.ktx)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //ROOM DATABASE
    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")

    //HILT
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    //ROBOLETRIC
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.robolectric:robolectric:4.13")

    //COROUTINES
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")

    //MOCKITO
    implementation("org.mockito:mockito-core:4.+")
    implementation("org.mockito:mockito-inline:3.11.2")

    //NECESSÁRIO PARA TESTAR VIEWMODEL
    testImplementation("androidx.test:core:1.2.0")
    testImplementation("android.arch.core:core-testing:1.1.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")

    //NAVEGAÇAO COMPOSE
    val nav_version = "2.7.7"

    implementation("androidx.navigation:navigation-compose:$nav_version")

    //livedata com compose
    implementation("androidx.compose.runtime:runtime-livedata:1.6.8")

    //Splash screen API
    implementation("androidx.core:core-splashscreen:1.0.0")

    //FIREBASE
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))


    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")


    // Add the dependencies for any other desired Firebase products
    // https://firebase.google.com/docs/android/setup#available-libraries


    //ADMOB
    implementation("com.google.android.gms:play-services-ads:23.2.0")
    implementation("androidx.window:window:1.3.0")

}
kapt {
    correctErrorTypes = true
}
hilt {
    enableAggregatingTask = false
}



