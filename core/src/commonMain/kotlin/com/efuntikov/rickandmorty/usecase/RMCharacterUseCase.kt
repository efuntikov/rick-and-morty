package com.efuntikov.rickandmorty.usecase

import com.badoo.reaktive.single.Single
import com.efuntikov.rickandmorty.data.entity.RMCharacter

interface RMCharacterUseCase: UseCase {
    fun getCharacterList(): Single<List<RMCharacter>>
}