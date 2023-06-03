package com.sample.data.character.local

import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Flowable
import org.junit.Test

internal class CharacterLocalDataSourceTest {

    private val dao = mockk<CharacterDao>()
    private val localDataSource = CharacterLocalDataSource(dao)

    @Test
    fun `when getting characters from local database, given success, then it should get characters`() {
        // Given
        val entities = listOf(entity())
        every { dao.getAll() } returns Flowable.just(entities)

        // When
        val stream = localDataSource.getCharacters().test()

        // Then
        stream.assertNoErrors()
        stream.assertComplete()
        stream.assertValue(entities)
    }

    @Test
    fun `when getting characters from local database, given error, then it should signal error`() {
        // Given
        val error = Exception()
        every { dao.getAll() } returns Flowable.error(error)

        // When
        val stream = localDataSource.getCharacters().test()

        // Then
        stream.assertNotComplete()
        stream.assertError(error)
    }

    private fun entity() = CharacterEntity(
        result = "",
        firstUrl = "",
        title = "",
        description = "",
        icon = IconEntity(
            height = 0,
            width = 0,
            url = ""
        )
    )
}
