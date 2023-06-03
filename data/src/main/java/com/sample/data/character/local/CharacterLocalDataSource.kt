package com.sample.data.character.local

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class CharacterLocalDataSource(
    private val dao: CharacterDao
) {
    fun getCharacters(): Flowable<List<CharacterEntity>> = dao.getAll()

    fun saveCharacters(entities: List<CharacterEntity>): Completable = dao.insertAll(entities)
}
