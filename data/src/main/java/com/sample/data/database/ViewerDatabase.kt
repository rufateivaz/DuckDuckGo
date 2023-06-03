package com.sample.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.data.character.local.CharacterDao
import com.sample.data.character.local.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1
)

abstract class ViewerDatabase : RoomDatabase() {
    abstract val characterDao: CharacterDao
}
