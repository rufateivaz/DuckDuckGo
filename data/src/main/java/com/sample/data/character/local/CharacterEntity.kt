package com.sample.data.character.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity

@Entity(
    tableName = "characters", primaryKeys = ["title"]
)
data class CharacterEntity(
    @ColumnInfo(name = "first_url")
    val firstUrl: String,
    val result: String,
    val title: String,
    val description: String,
    @Embedded(prefix = "icon_")
    val icon: IconEntity
)

data class IconEntity(
    val height: Int,
    val width: Int,
    val url: String
)