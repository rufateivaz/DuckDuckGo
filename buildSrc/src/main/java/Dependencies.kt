object Versions {
    object Config {
        const val compileSdk = 33
        const val minSdk = 23
        const val targetSdk = 33
        const val gradle = "8.0.1"
        const val kotlin = "1.8.21"
    }
    object UI {
        const val material = "1.9.0"
        const val appcompat = "1.6.1"
        const val recyclerView = "1.3.0"
        const val cardView = "1.0.0"
        const val glide = "4.15.1"
        const val slidingPaneLayout = "1.2.0"
    }
    object Data {
        const val room = "2.5.1"
        const val koin = "3.4.0"
    }
    object Network {
        const val rxJava = "3.1.5"
        const val rxKotlin = "3.0.1"
        const val rxAndroid = "3.0.2"
        const val retrofit = "2.9.0"
        const val gson = "2.10.1"
        const val okHttp = "4.10.0"
    }
    object Test {
        const val junit = "4.13.2"
        const val junitExt = "1.1.5"
        const val mockk = "1.13.5"
        const val espresso = "3.5.1"
        const val arch = "2.2.0"
    }
}

object Dependency {
    object UI {
        const val material = "com.google.android.material:material:${Versions.UI.material}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.UI.appcompat}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.UI.recyclerView}"
        const val cardView = "androidx.cardview:cardview:${Versions.UI.cardView}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.UI.glide}"
        const val slidingPaneLayout = "androidx.slidingpanelayout:slidingpanelayout:${Versions.UI.slidingPaneLayout}"
    }
    object Data {
        const val room = "androidx.room:room-runtime:${Versions.Data.room}"
        const val roomKapt = "androidx.room:room-compiler:${Versions.Data.room}"
        const val roomRxJava = "androidx.room:room-rxjava3:${Versions.Data.room}"
        const val koin = "io.insert-koin:koin-android:${Versions.Data.koin}"
    }

    object Network {
        const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.Network.rxJava}"
        const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:${Versions.Network.rxKotlin}"
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.Network.rxAndroid}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Network.retrofit}"
        const val retrofitAdapterRxJava3 = "com.squareup.retrofit2:adapter-rxjava3:${Versions.Network.retrofit}"
        const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.Network.retrofit}"
        const val gson = "com.google.code.gson:gson:${Versions.Network.gson}"
        const val okHttp = "com.squareup.okhttp3:okhttp"
        const val okHttpBom = "com.squareup.okhttp3:okhttp-bom:${Versions.Network.okHttp}"
        const val okHttpIntercepter = "com.squareup.okhttp3:logging-interceptor"
    }
    object Test {
        const val mockk = "io.mockk:mockk:${Versions.Test.mockk}"
        const val junit = "junit:junit:${Versions.Test.junit}"
        const val junitExt = "androidx.test.ext:junit:${Versions.Test.junitExt}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.Test.espresso}"
        const val arch = "androidx.arch.core:core-testing:${Versions.Test.arch}"
    }
}
