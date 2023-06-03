package com.sample.domain.characters.usecases

import com.sample.domain.characters.CharacterRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import org.junit.Test

class LoadCharactersUseCaseTest {
    private val characterRepository = mockk<CharacterRepository>()
    private val loadCharactersUseCase = LoadCharactersUseCase(characterRepository)

    @Test
    fun `when loading characters, given success, it should load the characters`() {
        // Given
        every { characterRepository.loadCharacters() } returns Completable.complete()

        // When
        val stream = loadCharactersUseCase().test()

        // Then
        stream.assertComplete()
    }

    @Test
    fun `when loading characters, given error, it should signal error`() {
        // Given
        val error = Exception()
        every { characterRepository.loadCharacters() } returns Completable.error(error)

        // When
        val stream = loadCharactersUseCase().test()

        // Then
        stream.assertNotComplete()
        stream.assertError(error)
    }
}
