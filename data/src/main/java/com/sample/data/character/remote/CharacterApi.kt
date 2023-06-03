package com.sample.data.character.remote

import com.sample.data.BuildConfig
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CharacterApi {
    @GET("?q=${BuildConfig.API_EXT}+characters&format=json")
    fun get(): Single<CharactersResponse>
}