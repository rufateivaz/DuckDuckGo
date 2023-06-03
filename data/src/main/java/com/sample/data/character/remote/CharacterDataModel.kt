package com.sample.data.character.remote

import com.google.gson.annotations.SerializedName

data class CharacterDataModel(
    @SerializedName("FirstURL") val firstUrl: String,
    @SerializedName("Icon") val icon: IconDataModel,
    @SerializedName("Result") val result: String,
    @SerializedName("Text") val text: String
)

data class IconDataModel(
    @SerializedName("URL") val url: String,
    @SerializedName("Height") val height: String,
    @SerializedName("Width") val width: String
)

data class CharactersResponse(
    @SerializedName("RelatedTopics")
    val charactersDataModel: List<CharacterDataModel>
)
