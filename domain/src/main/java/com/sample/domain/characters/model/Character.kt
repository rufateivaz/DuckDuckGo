package com.sample.domain.characters.model

/**
 *  [Character] object that represent each character in domain model.
 *
 *  @param firstUrl first url of character
 *  @param icon the icon info of character
 *  @param result the result data of character
 *  @param title the title of the character
 *  @param description the description of the character
 */
data class Character(
    val firstUrl: String,
    val icon: Icon,
    val result: String,
    val title: String,
    val description: String
)
/**
 *  [Character] object that represent each character in domain model.
 *
 *  @param url the url of icon
 *  @param height the height of the icon
 *  @param width the width of the icon
 */
data class Icon(
    val url: String,
    val height: Int,
    val width: Int
)
