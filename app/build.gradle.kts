plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp") version "2.0.0-1.0.21"
}

android {
    compileSdk = 34
    buildToolsVersion = "33.0.1"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    defaultConfig {
        applicationId = "com.example.androidplayground"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
    sourceSets.getByName("main") {
            java.srcDirs("${buildDir.absolutePath}/generated/source/kaptKotlin/")
    }
    namespace = "com.example.androidplayground"
}

dependencies {
    //implementation(fileTree(dir: 'libs', include: ['*.jar']))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:2.0.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.core:core-ktx:1.13.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Gson
    implementation("com.google.code.gson:gson:2.8.9")

    // RXJava
    implementation("io.reactivex.rxjava2:rxandroid:2.0.2")

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.12.0")

    // Image Loading
    implementation("io.coil-kt:coil:2.4.0")
    implementation("com.github.bumptech.glide:glide:4.15.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    // Jetpack compose
    implementation("androidx.compose.ui:ui:1.6.8")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.6.8")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.6.8")
    // Material Design
    implementation("androidx.compose.material:material:1.6.8")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.6.8")
    implementation("androidx.compose.material:material-icons-extended:1.6.8")
    // Integration with activities
    implementation("androidx.activity:activity-compose:1.9.0")
    // Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.6.8")
    implementation("androidx.compose.runtime:runtime-rxjava3:1.6.8")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.8")

    // Unit Tests
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

    // Dagger
    implementation("com.google.dagger:dagger:2.45")
    ksp("com.google.dagger:dagger-compiler:2.45")

    implementation(project(":processor"))

}
