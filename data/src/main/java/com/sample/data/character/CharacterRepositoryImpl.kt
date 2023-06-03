package com.sample.data.character

import com.sample.data.character.extensions.toCharacter
import com.sample.data.character.extensions.toCharacterEntity
import com.sample.data.character.local.CharacterLocalDataSource
import com.sample.data.character.remote.CharacterRemoteDataSource
import com.sample.domain.characters.CharacterRepository
import com.sample.domain.characters.model.Character
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class CharacterRepositoryImpl(
    private val localDataSource: CharacterLocalDataSource,
    private val remoteDataSource: CharacterRemoteDataSource
) : CharacterRepository {

    override fun getCharacters(): Flowable<List<Character>> =
        localDataSource.getCharacters()
        .map { it.map { characterEntity -> characterEntity.toCharacter() } }

    override fun loadCharacters(): Completable =
        remoteDataSource.getResponseData()
        .map { response -> response.charactersDataModel.map { it.toCharacterEntity() } }
        .flatMapCompletable { localDataSource.saveCharacters(it) }
}