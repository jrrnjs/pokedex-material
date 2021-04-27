object Deps {

    object TestLib {
        const val junit = "junit:junit:${Versions.junit}"
    }

    object AndroidTestLib {
        const val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

    object Gradle {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    }

    object Lib {

        object Kotlin {
            const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
            const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
        }

        object Android {
            const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
            const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
            const val material = "com.google.android.material:material:${Versions.material}"

            const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
            const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        }

        object Lifecycle {
            const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
            const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        }

        object Startup {
            const val startup = "androidx.startup:startup-runtime:${Versions.startup}"
        }

        object Room {
            const val room = "androidx.room:room-runtime:${Versions.room}"
            const val compiler = "androidx.room:room-compiler:${Versions.room}"
            const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
        }

        object Hilt {
            const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
            const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        }

        object Json {
            const val gson = "com.google.code.gson:gson:${Versions.gson}"
        }

        object Network {
            const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
            const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
            const val okhttpBOM = "com.squareup.okhttp3:okhttp-bom:${Versions.okhttp}"
            const val okhttp = "com.squareup.okhttp3:okhttp"
            const val loggingIntercepter = "com.squareup.okhttp3:logging-interceptor"
            const val sandwich = "com.github.skydoves:sandwich:${Versions.sandwich}"
        }

        object Glide {
            const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
            const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
            const val palette = "com.github.florent37:glidepalette:${Versions.glidePalette}"
        }

        object Logger {
            const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        }

        object CustomUI {
            const val transformationLayout = "com.github.skydoves:transformationlayout:${Versions.transformationLayout}"
            const val recyclerViewDivider = "com.github.fondesa:recycler-view-divider:${Versions.recyclerViewDivider}"
            const val progressView = "com.github.skydoves:progressview:${Versions.progressView}"
        }
    }
}