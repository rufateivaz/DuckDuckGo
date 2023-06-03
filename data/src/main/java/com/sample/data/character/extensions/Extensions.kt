package com.sample.data.character.extensions

import com.sample.data.character.local.CharacterEntity
import com.sample.data.character.local.IconEntity
import com.sample.data.character.remote.CharacterDataModel
import com.sample.data.character.remote.IconDataModel
import com.sample.domain.characters.model.Character
import com.sample.domain.characters.model.Icon

fun extractTitleAndDesc(str: String): List<String> {
    return if (str.isEmpty()) listOf("", "")
    else {
        val index = str.indexOfFirst { it == '-' }
        if (index < 0) listOf(str, "")
        else listOf(str.substring(0, index), str.substring(index + 1).trim())
    }
}

fun CharacterDataModel.toCharacterEntity(): CharacterEntity {
    val titleAndDesc = extractTitleAndDesc(text)
    return CharacterEntity(
        firstUrl = firstUrl,
        result = result,
        title = titleAndDesc[0],
        description = titleAndDesc[1],
        icon = icon.toIconEntity()
    )
}

fun CharacterEntity.toCharacter(): Character = Character(
    firstUrl = firstUrl,
    result = result,
    title = title,
    description = description,
    icon = icon.toIcon()
)

fun IconDataModel.toIconEntity() = IconEntity(
    height = if (height.isEmpty()) 0 else height.toInt(),
    width = if (width.isEmpty()) 0 else width.toInt(),
    url = url
)

fun IconEntity.toIcon() = Icon(
    height = height,
    width = width,
    url = url
)