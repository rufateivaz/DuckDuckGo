package com.sample.viewer.injection

import com.sample.viewer.ViewerActivityViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val uiModule = module {
    viewModel { ViewerActivityViewModel(
        loadCharactersUseCase = get(),
        getCharactersUseCase = get(),
        backgroundScheduler = Schedulers.io(),
        mainScheduler = AndroidSchedulers.mainThread()
    ) }
}

private val loadUIModules by lazy {
    loadKoinModules(
        listOf(
            uiModule
        )
    )
}

fun injectUIFeatures() = loadUIModules