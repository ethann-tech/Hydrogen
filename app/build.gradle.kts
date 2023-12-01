import java.util.Date
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}


android {
    compileSdk = 34
    namespace = libs.versions.packageName.get()
    defaultConfig {
        multiDexEnabled = true
        applicationId = "com.ethan.hydrogen.demo"
        minSdk = 26
        versionCode = 12
        versionName = "v0.1.9"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner" //setProperty('archivesBaseName', "$applicationId-official-$versionName-$versionCode-"+releaseTime())

    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }

    //signingConfigs {
    //    getByName("release") {
    //
    //        storeFile =file("/keystore/release.jks")
    //        storePassword ="ethan0000"
    //        keyAlias ="release"
    //        keyPassword= "ethan0000"
    //    }
    //    getByName("debug") {
    //
    //        storeFile = file("/keystore/release.jks")
    //        storePassword ="ethan0000"
    //        keyAlias= "release"
    //        keyPassword= "ethan0000"
    //    }
    //}
    buildTypes {
        debug {
            isDebuggable =true
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro","proguard-dic-6.txt","proguard-log.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    // 测试相关
    implementation(libs.permissions.xxpermissions)
    debugImplementation (libs.leakcanary.android)
    implementation (libs.imagepicker)  // 图片视频选择器

    implementation(libs.butterfly)
    implementation(libs.butterfly.compose)
    kapt(libs.butterfly.compiler)

    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation(project(mapOf("path" to ":library")))
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.android.ext)
    androidTestImplementation(libs.test.android.espresso)
    implementation(libs.kotlin.stdlib)
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
    implementation(libs.glide)
    implementation(libs.slf4j.api)
    implementation(libs.logback.android)
    implementation(libs.toaster)
    implementation(libs.baserecyclerviewadapterhelper)
    implementation(libs.qmuiteam.qmui)
    implementation(libs.qmuiteam.arch)
    kapt(libs.qmuiteam.arch.compiler)


}
