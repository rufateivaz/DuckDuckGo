package com.sample.domain.characters.usecases

import com.sample.domain.characters.CharacterRepository
import com.sample.domain.characters.model.Character
import com.sample.domain.characters.model.Icon
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Flowable
import org.junit.Test

class GetCharactersUseCaseTest {

    private val characterRepository = mockk<CharacterRepository>()
    private val getCharactersUseCaseTest = GetCharactersUseCase(characterRepository)

    @Test
    fun `when getting characters, given success, it should filter with respect to search content`() {
        // Given
        val character1 = character(title = "tExT")
        val character2 = character(description = "TeXt")
        val character3 = character()
        every {
            characterRepository.getCharacters()
        } returns Flowable.just(listOf(character1, character2, character3))

        // When
        val stream = getCharactersUseCaseTest("text").test()

        // Then
        stream.assertValue(listOf(character1, character2))
    }

    @Test
    fun `when getting characters, given error, it should signal error`() {
        // Given
        val error = Exception()
        every { characterRepository.getCharacters() } returns Flowable.error(error)

        // When
        val stream = getCharactersUseCaseTest("").test()

        // Then
        stream.assertNotComplete()
        stream.assertError(error)
    }

    private fun character(title: String = "", description: String = "") = Character(
        firstUrl = "",
        title = title,
        description = description,
        result = "",
        icon = Icon(
            url = "",
            height = 0,
            width = 0
        )
    )
}
