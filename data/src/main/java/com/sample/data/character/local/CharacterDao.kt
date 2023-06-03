package com.sample.data.character.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<CharacterEntity>): Completable

    @Query("SELECT * FROM characters")
    fun getAll(): Flowable<List<CharacterEntity>>
}
