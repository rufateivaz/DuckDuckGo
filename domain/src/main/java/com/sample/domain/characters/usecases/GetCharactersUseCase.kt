package com.sample.domain.characters.usecases

import com.sample.domain.characters.CharacterRepository
import io.reactivex.rxjava3.core.Flowable
import com.sample.domain.characters.model.Character

/**
 *  Use case that gets characters by search content.
 *
 *  @param repository that represents CharacterRepository interface.
 */
class GetCharactersUseCase(private val repository: CharacterRepository) {

    /**
     *  Observes the flow of the characters from local database.
     *
     *  When there is any new update in the table, this method is
     *  triggered such that it gets the latest up to date list of characters.
     *  Then the list is filtered w.r.t the search content.
     *
     *  @param searchContent the content of the search box - empty by default.
     *
     *  @return [Flowable] that emits when the table is updated, or does not emit anything.
     */
    operator fun invoke(searchContent: String = ""): Flowable<List<Character>> =
        repository.getCharacters()
            .map { characters ->
                characters.filter { character ->
                    character.title.contains(searchContent, ignoreCase = true)
                            || character.description.contains(searchContent, ignoreCase = true)
                }
            }
}