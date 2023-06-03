package com.sample.data.character.remote

import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import java.lang.Exception

class CharacterRemoteDataSourceTest {

    private val api = mockk<CharacterApi>()
    private val remoteDataSource = CharacterRemoteDataSource(api)

    @Test
    fun `when getting character data model, given success, then it should get the data model`() {
        // Given
        val listOfCharacterDataModel = listOf(characterDataModel())
        val charactersResponse = CharactersResponse(listOfCharacterDataModel)
        every { api.get() } returns Single.just(charactersResponse)

        // When
        val stream = remoteDataSource.getResponseData().test()

        // Then
        stream.assertComplete()
        stream.assertNoErrors()
        stream.assertValue(charactersResponse)
    }

    @Test
    fun `when getting character data model, given error, then it should signal error`() {
        // Given
        val error = Exception()
        every { api.get() } returns Single.error(error)

        // When
        val stream = remoteDataSource.getResponseData().test()

        // Then
        stream.assertNotComplete()
        stream.assertError(error)
    }

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
