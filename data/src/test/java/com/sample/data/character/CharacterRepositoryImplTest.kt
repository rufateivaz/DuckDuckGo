package com.sample.data.character

import com.sample.data.character.local.CharacterEntity
import com.sample.data.character.local.CharacterLocalDataSource
import com.sample.data.character.local.IconEntity
import com.sample.data.character.remote.CharacterDataModel
import com.sample.data.character.remote.CharacterRemoteDataSource
import com.sample.data.character.remote.CharactersResponse
import com.sample.data.character.remote.IconDataModel
import com.sample.domain.characters.model.Character
import com.sample.domain.characters.model.Icon
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import org.junit.Test

internal class CharacterRepositoryImplTest {
    private val localDataSource = mockk<CharacterLocalDataSource>()
    private val remoteDataSource = mockk<CharacterRemoteDataSource>()

    private val repository = CharacterRepositoryImpl(localDataSource, remoteDataSource)

    @Test
    fun `when getting characters, given success, then it should get the characters`() {
        // Given
        val listOfEntities = listOf(characterEntity())
        every { localDataSource.getCharacters() } returns Flowable.just(listOfEntities)

        // When
        val stream = repository.getCharacters().test()

        // Then
        stream.assertComplete()
        stream.assertNoErrors()

        val listOfCharacters = listOf(character()) // expected
        stream.assertValue(listOfCharacters)
    }

    @Test
    fun `when getting characters, given error, then it should signal error`() {
        // Given
        val error = Exception()

        every { localDataSource.getCharacters() } returns Flowable.error(error)

        // When
        val stream = repository.getCharacters().test()

        // Then
        stream.assertNotComplete()
        stream.assertError(error)
    }

    @Test
    fun `when loading characters, given success, then it should save them into local database`() {
        // Given
        val charactersResponse = CharactersResponse(listOf(characterDataModel()))
        val characterEntities = listOf(characterEntity())

        every { remoteDataSource.getResponseData() } returns Single.just(charactersResponse)
        every { localDataSource.saveCharacters(characterEntities) } returns Completable.complete()

        // When
        val stream = repository.loadCharacters().test()

        // Then
        stream.assertComplete()
        stream.assertNoErrors()
        verify {
            remoteDataSource.getResponseData()
            localDataSource.saveCharacters(characterEntities)
        }
    }

    @Test
    fun `when loading characters, given error on loading, then it should signal error`() {
        // Given
        val error = Exception()
        every { remoteDataSource.getResponseData() } returns Single.error(error)

        // When
        val stream = repository.loadCharacters().test()

        // Then
        stream.assertNotComplete()
        stream.assertError(error)

        verify(exactly = 0) {
            localDataSource.saveCharacters(any())
        }
    }

    @Test
    fun `when loading characters, given error on saving, then it should signal error`() {
        // Given
        val error = Exception()
        val charactersResponse = CharactersResponse(listOf(characterDataModel()))
        every { remoteDataSource.getResponseData() } returns Single.just(charactersResponse)
        every { localDataSource.saveCharacters(any()) } returns Completable.error(error)

        // When
        val stream = repository.loadCharacters().test()

        // Then
        stream.assertNotComplete()
        stream.assertError(error)
    }

    private fun character() = Character(
        firstUrl = "",
        result = "",
        title = "",
        description = "",
        icon = Icon(
            height = 0,
            width = 0,
            url = ""
        )
    )

    private fun characterEntity() = CharacterEntity(
        firstUrl = "",
        result = "",
        title = "",
        description = "",
        icon = IconEntity(
            height = 0,
            width = 0,
            url = ""
        )

    )

    private fun characterDataModel() = CharacterDataModel(
        firstUrl = "",
        result = "",
        text = "",
        icon = IconDataModel(
            width = "",
            height = "",
            url = ""
        )
    )
}
