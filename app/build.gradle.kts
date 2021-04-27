plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(Versions.App.compileSdk)
    buildToolsVersion(Versions.App.buildTools)

    defaultConfig {
        applicationId("com.klobalvar.pokedex")
        minSdkVersion(Versions.App.minSdk)
        targetSdkVersion(Versions.App.targetSdk)
        versionCode(Versions.App.versionCode)
        versionName(Versions.App.versionName)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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
        dataBinding = true
    }
}

dependencies {

    with(Deps.TestLib) {
        testImplementation(junit)
    }

    with(Deps.AndroidTestLib) {
        androidTestImplementation(androidxJunit)
        androidTestImplementation(espresso)
    }


    with(Deps.Lib.Kotlin) {
        implementation(kotlin)
        implementation(coroutine)
    }

    with(Deps.Lib.Android) {
        implementation(appcompat)
        implementation(recyclerView)
        implementation(constraintLayout)
        implementation(material)
        implementation(coreKtx)
        implementation(activityKtx)
        implementation(fragmentKtx)
    }

    with(Deps.Lib.Lifecycle) {
        implementation(lifecycleKtx)
        implementation(liveDataKtx)
        implementation(viewModelKtx)
    }

    with(Deps.Lib.Startup) {
        implementation(startup)
    }

    with(Deps.Lib.Room) {
        implementation(room)
        kapt(compiler)
        implementation(roomKtx)
    }

    with(Deps.Lib.Hilt) {
        implementation(hilt)
        kapt(compiler)
    }

    with(Deps.Lib.Json) {
        implementation(gson)
    }

    with(Deps.Lib.Network) {
        implementation(retrofit)
        implementation(gsonConverter)
        implementation(platform(okhttpBOM))
        implementation(okhttp)
        implementation(loggingIntercepter)
        implementation(sandwich)
    }

    with(Deps.Lib.Glide) {
        implementation(glide)
        kapt(compiler)
        implementation(palette)
    }

    with(Deps.Lib.Logger) {
        implementation(timber)
    }

    with(Deps.Lib.CustomUI) {
        implementation(transformationLayout)
        implementation(recyclerViewDivider)
        implementation(progressView)
    }
}