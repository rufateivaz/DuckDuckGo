package com.sample.domain.characters.usecases

import com.sample.domain.characters.CharacterRepository
import io.reactivex.rxjava3.core.Completable

/**
 *  Use case that loads the characters.
 *
 *  @param repository that represents CharacterRepository interface.
 */
class LoadCharactersUseCase(private val repository: CharacterRepository) {

    /**
     *  Loads the characters.
     *
     *  @return [Completable] that signals completed/error depending on the load operation.
     */
    operator fun invoke(): Completable = repository.loadCharacters()
}