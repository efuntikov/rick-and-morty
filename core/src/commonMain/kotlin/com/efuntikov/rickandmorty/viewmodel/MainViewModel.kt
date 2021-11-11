package com.efuntikov.rickandmorty.viewmodel

import com.badoo.reaktive.single.subscribe
import com.badoo.reaktive.subject.publish.PublishSubject
import com.efuntikov.rickandmorty.data.entity.RMCharacter
import com.efuntikov.rickandmorty.usecase.RMApiUseCaseImpl
import com.efuntikov.rickandmorty.usecase.RMCharacterUseCaseImpl

enum class MainViewModelNavigation {
    TO_MAIN_SCREEN
}

interface MainViewModelInput {
    fun load()
}

interface MainViewModelOutput {
    val loading: PublishSubject<Boolean>
    val navigation: PublishSubject<MainViewModelNavigation>
}

interface MainViewModel {
    val inputs: MainViewModelInput
    val outputs: MainViewModelOutput
}

class MainViewModelImpl : MainViewModel, MainViewModelOutput, MainViewModelInput {

    override val inputs: MainViewModelInput = this
    override val outputs: MainViewModelOutput = this

    override val loading: PublishSubject<Boolean> = PublishSubject()
    override val navigation: PublishSubject<MainViewModelNavigation> = PublishSubject()

    override fun load() {
        loading.onNext(false)


    }

}