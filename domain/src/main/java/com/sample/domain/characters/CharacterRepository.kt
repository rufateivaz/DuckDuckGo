package com.sample.domain.characters

import com.sample.domain.characters.model.Character
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

/**
 *  Repository that is able to access to both remote and local data sources.
 */
interface CharacterRepository {
    /**
     *  Observes the flow of the characters from local database.
     *
     *  When there is any new update in the table, this method is
     *  triggered such that it gets the latest up to date list of characters.
     *  Then the list is filtered w.r.t the search content.
     *
     *  @return [Flowable] that emits when the table is updated, or does not emit anything.
     */
    fun getCharacters(): Flowable<List<Character>>

    /**
     *  Loads characters from endpoint to local database.
     *
     *  @return [Completable] that signals completed/error depending on the load operation.
     */
    fun loadCharacters(): Completable
}