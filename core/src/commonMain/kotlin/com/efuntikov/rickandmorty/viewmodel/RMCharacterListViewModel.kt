package com.efuntikov.rickandmorty.viewmodel

import com.badoo.reaktive.single.subscribe
import com.badoo.reaktive.subject.publish.PublishSubject
import com.efuntikov.rickandmorty.data.entity.RMCharacter
import com.efuntikov.rickandmorty.usecase.RMApiUseCaseImpl
import com.efuntikov.rickandmorty.usecase.RMCharacterUseCaseImpl

enum class RMCharacterListViewModelNavigation {
    TO_CHARACTER_DETAIL
}

interface RMCharacterListViewModelInput {
    fun load()
    fun getCharacterList(): List<RMCharacter>
}

interface RMCharacterListViewModelOutput {
    val loading: PublishSubject<Boolean>
    val characters: PublishSubject<Int>
    val charactersList: PublishSubject<List<RMCharacter>>
    val navigation: PublishSubject<RMCharacterListViewModelNavigation>
}

interface RMCharacterListViewModel {
    val inputs: RMCharacterListViewModelInput
    val outputs: RMCharacterListViewModelOutput
}

class RMCharacterListViewModelImpl : RMCharacterListViewModel,
    RMCharacterListViewModelOutput, RMCharacterListViewModelInput {

    private val rmCharacterList = ArrayList<RMCharacter>()

    override val inputs: RMCharacterListViewModelInput = this
    override val outputs: RMCharacterListViewModelOutput = this

    override val loading: PublishSubject<Boolean> = PublishSubject()
    override val characters: PublishSubject<Int> = PublishSubject()
    override val charactersList: PublishSubject<List<RMCharacter>> = PublishSubject()
    override val navigation: PublishSubject<RMCharacterListViewModelNavigation> = PublishSubject()

    override fun load() {
        loading.onNext(true)

        RMCharacterUseCaseImpl.getCharacterList().subscribe {
            loading.onNext(false)
            characters.onNext(it.size)
            rmCharacterList.addAll(it)
            charactersList.onNext(rmCharacterList)
        }

    }

    override fun getCharacterList() = rmCharacterList

}