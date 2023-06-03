package com.sample.data.network.injection

import com.sample.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory<CallAdapter.Factory> {
        RxJava3CallAdapterFactory.create()
    }

    factory<Converter.Factory> {
        GsonConverterFactory.create()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API)
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .client(basicOkHttpClient())
            .build()
    }
}

fun httpInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

fun basicOkHttpClient() = OkHttpClient.Builder()
    .addInterceptor(httpInterceptor())
    .build()

