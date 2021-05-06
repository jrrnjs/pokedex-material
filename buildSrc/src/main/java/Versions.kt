object Versions {

    object App {
        const val compileSdk = 30
        const val buildTools = "30.0.2"
        const val minSdk = 21
        const val targetSdk = 30

        private const val versionMajor = 1
        private const val versionMinor = 0
        private const val versionPatch = 0
        const val versionCode = versionMajor * 10000 + versionMinor * 100 + versionPatch
        const val versionName = "${versionMajor}.${versionMinor}.${versionPatch}"
    }

    // test
    const val junit = "4.13.2"

    // android text
    const val androidxJunit = "1.1.2"
    const val espresso = "3.3.0"

    // gradle
    const val gradle = "4.1.3"

    // kotlin
    const val kotlin = "1.4.31"
    const val coroutine = "1.4.3"

    // android
    const val appcompat = "1.2.0"
    const val recyclerView = "1.2.0"
    const val constraintLayout = "2.0.4"
    const val material = "1.3.0"

    const val coreKtx = "1.3.2"
    const val activityKtx = "1.3.0-alpha05"
    const val fragmentKtx = "1.3.3"


    // lifecycle, liveData, viewModel
    const val lifecycle = "2.3.1"

    // startup
    const val startup = "1.0.0"

    // room
    const val room = "2.2.6"

    // hilt
    const val hilt = "2.35.1"

    // json
    const val gson = "2.8.6"

    // network
    const val retrofit = "2.9.0"
    const val okhttp = "4.9.0"
    const val sandwich = "1.1.0"

    // glide
    const val glide = "4.12.0"
    const val glidePalette = "2.1.2"

    // timber
    const val timber = "4.7.1"

    // custom ui
    const val transformationLayout = "1.0.8"
    const val recyclerViewDivider = "3.5.0"
    const val progressView = "1.1.1"
}