# pokedex-material

## !!!
🗡️[Pokedex](https://github.com/skydoves/Pokedex) 해당 repo를 참고하여 만든 clone, cover 앱 repo이다.

## 소개
Jetpack, MVVM architecutre 기반의 Pokedex 안드로이드 앱으로
<br>
해당 프로젝트는 Hilt 라이브러리를 통해 DI를 Room Database와 Retrofit을 통한 Rest API를 이용하여 Respository를 구현하였다.
<br>
Material Design, Theme를 활용하였으며 Light Mode, Dark Mode가 구현되어 있다.

## 스크린샷
Light Mode
<p>
<img src="/screenshots/light_mode_main" width="25%"/>
<img src="/screenshots/light_mode_backdrop" width="25%"/>
<img src="/screenshots/light_mode_profile" width="25%"/>
<img src="/screenshots/light_mode_popup" width="25%"/>
</p>
Dark Mode
<p>
<img src="/screenshots/dark_mode_main" width="25%"/>
<img src="/screenshots/dark_mode_backdrop" width="25%"/>
<img src="/screenshots/dark_mode_profile" width="25%"/>
<img src="/screenshots/dark_mode_popup" width="25%"/>
</p>

## 버전 & 라이브러리 
- minSdkVersion 21
- targetSdkVersion 30
- [Kotlin](https://kotlinlang.org/) - v1.4.31
  - [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
  - [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
- Jetpack
  - Lifecycle
  - LiveData
  - ViewModel
  - Room Database
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=ko) - v2.33-beta
- Network
  - Retrofit2
  - OkHttp3
  - [Sandwich](https://github.com/skydoves/Sandwich)
- Gson
- Image
  - Glide
  - [GlidePalette](https://github.com/florent37/GlidePalette)
- [TransformationLayout](https://github.com/skydoves/transformationlayout)
- [RecyclerViewDivider](https://github.com/fondesa/recycler-view-divider)
- [ProgressView](https://github.com/skydoves/progressview)

## 아키텍쳐
Repository 패턴과 MVVM 아키텍처를 기반으로 한다.
<br>
ViewModel, LiveData, DataBinding를 이용하여 데이터를 구독하는 형태를 취하며 ViewModel은 Repository와 통신하여 데이터를 획득한다.
<br>
Repository는 local database와 rest api를 적절히 활용하여 데이터를 읽고 저장하는 형태를 가진다.

## Material Theming


### [Color](https://material.io/develop/android/theming/color/)
Light, Dark theme에 따라 한 쌍의 color set를 가진다. [color.xml](app/src/main/res/values/color.xml)에서 전체 Color를 확인할 수 있으며 [default theme(light)](app/src/main/res/values/theme.xml)와 [dark theme](app/src/main/res/values-night/theme.xml)에서 테마별 설정 정보를 확인할 수 있다.

### [Shape](https://material.io/develop/android/theming/shape/)
[shape.xml](app/src/main/res/values/shape.xml)

### [Typography](https://material.io/develop/android/theming/typography/).
[Rubik](https://fonts.google.com/specimen/Rubik)

### [Motion](https://material.io/develop/android/theming/motion)
[TransformationLayout](https://github.com/skydoves/transformationlayout) 해당 라이브러리를 활용하여 구현

## Rest API
[PokeAPI](https://pokeapi.co/) 
- /pokemon
- /pokemon/{name}
- /type/{type}
