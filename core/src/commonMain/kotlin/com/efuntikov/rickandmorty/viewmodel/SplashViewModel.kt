package com.efuntikov.rickandmorty.viewmodel

import com.badoo.reaktive.single.subscribe
import com.badoo.reaktive.subject.publish.PublishSubject
import com.efuntikov.rickandmorty.usecase.RMApiUseCaseImpl

enum class SplashViewModelNavigation {
    TO_MAIN_SCREEN
}

interface SplashViewModelInput {
    fun load()
}

interface SplashViewModelOutput {
    val loading: PublishSubject<Boolean>
    val navigation: PublishSubject<SplashViewModelNavigation>
}

interface SplashViewModel {
    val inputs: SplashViewModelInput
    val outputs: SplashViewModelOutput
}

class SplashViewModelImpl : SplashViewModel, SplashViewModelOutput, SplashViewModelInput {

    override val inputs: SplashViewModelInput = this
    override val outputs: SplashViewModelOutput = this

    override val loading: PublishSubject<Boolean> = PublishSubject()
    override val navigation: PublishSubject<SplashViewModelNavigation> = PublishSubject()

    override fun load() {
        loading.onNext(true)

        RMApiUseCaseImpl.getApi().subscribe {
            loading.onNext(false)
            navigation.onNext(SplashViewModelNavigation.TO_MAIN_SCREEN)
        }

    }

}