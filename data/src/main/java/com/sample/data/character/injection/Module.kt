package com.sample.data.character.injection

import com.sample.data.character.CharacterRepositoryImpl
import com.sample.data.character.local.CharacterLocalDataSource
import com.sample.data.character.remote.CharacterApi
import com.sample.data.character.remote.CharacterRemoteDataSource
import com.sample.domain.characters.CharacterRepository
import com.sample.domain.characters.usecases.GetCharactersUseCase
import com.sample.domain.characters.usecases.LoadCharactersUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

val characterDataModule = module {
    fun provideCharacterApi(retrofit: Retrofit): CharacterApi =
        retrofit.create(CharacterApi::class.java)

    single<CharacterRepository> {
        CharacterRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
    single { CharacterLocalDataSource(dao = get()) }
    single { CharacterRemoteDataSource(characterApi = get()) }
    single { GetCharactersUseCase(repository = get()) }
    single { LoadCharactersUseCase(repository = get()) }
    single { provideCharacterApi(retrofit = get()) }
}