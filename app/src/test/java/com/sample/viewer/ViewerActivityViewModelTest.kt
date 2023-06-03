package com.sample.viewer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.domain.characters.model.Character
import com.sample.domain.characters.model.Icon
import com.sample.domain.characters.usecases.GetCharactersUseCase
import com.sample.domain.characters.usecases.LoadCharactersUseCase
import getOrAwaitValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals
import java.util.concurrent.TimeoutException

class ViewerActivityViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val main = Schedulers.trampoline()
    private val bg = Schedulers.trampoline()

    private val getUseCase = mockk<GetCharactersUseCase>()
    private val loadUseCase = mockk<LoadCharactersUseCase>()

    private fun createViewModel() = ViewerActivityViewModel(
        loadCharactersUseCase = loadUseCase,
        getCharactersUseCase = getUseCase,
        mainScheduler = main,
        backgroundScheduler = bg
    )

    @Test
    fun `when creating viewModel, given init is processing, it should start loading and getting methods`() {
        // Given
        every { getUseCase(any()) } returns Flowable.just(emptyList())
        every { loadUseCase() } returns Completable.complete()

        // When
        createViewModel()

        // Then
        verify(exactly = 1) {
            loadUseCase()
            getUseCase("")
        }
    }

    @Test
    fun `when getting characters, given success, it should get characters`() {
        // Given
        val c1 = character().copy(title = "t1")
        val c2 = character().copy(title = "t2")
        val list = listOf(c1, c2)
        every { getUseCase(any()) } returns Flowable.just(list)
        every { loadUseCase() } returns Completable.complete()

        // When
        val vm = createViewModel()

        // Then
        assertEquals(list, vm.characters.getOrAwaitValue())
    }

    @Test(expected = TimeoutException::class)
    fun `when getting characters, given error, it should not get any value`() {
        // Given
        val error = Exception()
        every { getUseCase(any()) } returns Flowable.error(error)
        every { loadUseCase() } returns Completable.complete()

        // When
        val vm = createViewModel()

        // Then
        vm.characters.getOrAwaitValue() // will throw timeout exception
    }

    @Test
    fun `when a character is selected, it should be selected`() {
        // Given
        val c = character().copy(title = "selected")
        every { getUseCase(any()) } returns Flowable.just(emptyList())
        every { loadUseCase() } returns Completable.complete()

        // When
        val vm = createViewModel()
        vm.setSelectedCharacter(c)

        // Then
        assertEquals(c, vm.selectedCharacter.value)
    }

    private fun character() = Character(
        title = "",
        description = "",
        result = "",
        icon = Icon(
            url = "",
            width = 0,
            height = 0
        ),
        firstUrl = ""
    )
}