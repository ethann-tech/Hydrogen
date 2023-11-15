plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
android {
    compileSdk = 34
    namespace = "com.ethan.hydrogen"
    defaultConfig {
        minSdk = 26
    }

    buildTypes {
        release { // 代码混淆 false 不混淆，true 混淆
            isMinifyEnabled = false // zipalign 优化 这个也要单独领出来说一下
            isZipAlignEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies { //implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation(files("libs\\zxing-core-3.3.3.jar"))
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.android.ext)
    androidTestImplementation(libs.test.android.espresso)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.gson)
    implementation(libs.slf4j.api)
    implementation(libs.logback.android)
    implementation(libs.toaster)
}


