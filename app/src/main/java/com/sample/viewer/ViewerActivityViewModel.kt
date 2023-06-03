package com.sample.viewer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.domain.characters.model.Character
import com.sample.domain.characters.usecases.GetCharactersUseCase
import com.sample.domain.characters.usecases.LoadCharactersUseCase
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy

class ViewerActivityViewModel(
    private val loadCharactersUseCase: LoadCharactersUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val backgroundScheduler: Scheduler,
    private val mainScheduler: Scheduler
) : ViewModel() {

    private val disposable by lazy {
        CompositeDisposable()
    }

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> get() = _characters

    private val _selectedCharacter = MutableLiveData<Character>()
    val selectedCharacter: LiveData<Character> get() = _selectedCharacter

    init {
        loadCharacters()
        getCharacters()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    private fun loadCharacters() {
        loadCharactersUseCase()
            .subscribeOn(backgroundScheduler)
            .observeOn(mainScheduler)
            .subscribeBy(
                onComplete = {
                    // If required, can be extended to anything else.
                },
                onError = {
                    // Can be handled by pop-up error message, but not important now.
                }
            ).addTo(disposable)
    }

    fun getCharacters(searchContent: String = "") {
        getCharactersUseCase(searchContent)
            .subscribeOn(backgroundScheduler)
            .observeOn(mainScheduler)
            .subscribeBy(
                onNext = {
                    _characters.value = it
                },
                onError = {
                    // Can be handled by pop-up error message, but not important now.
                }
            ).addTo(disposable)
    }

    fun setSelectedCharacter(selected: Character) {
        _selectedCharacter.value = selected
    }
}