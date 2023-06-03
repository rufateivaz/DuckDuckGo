package com.sample.data.character.remote

import io.reactivex.rxjava3.core.Single

class CharacterRemoteDataSource(
    private val characterApi: CharacterApi
) {
    fun getResponseData(): Single<CharactersResponse> = characterApi.get()
}