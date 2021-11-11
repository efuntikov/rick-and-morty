package com.efuntikov.rickandmorty.usecase

import com.badoo.reaktive.scheduler.createMainScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.flatMap
import com.badoo.reaktive.single.map
import com.badoo.reaktive.single.observeOn
import com.efuntikov.rickandmorty.CoroutineExtensions
import com.efuntikov.rickandmorty.data.ApisMapper
import com.efuntikov.rickandmorty.data.RMCharacterMapper
import com.efuntikov.rickandmorty.data.entity.RMCharacter
import com.efuntikov.rickandmorty.repository.RMApiRepositoryImpl
import com.efuntikov.rickandmorty.repository.RMCharacterRepositoryImpl

object RMCharacterUseCaseImpl : RMCharacterUseCase {

    private val mapper: RMCharacterMapper = RMCharacterMapper()

    override fun getCharacterList(): Single<List<RMCharacter>> {
        return CoroutineExtensions.singleFromCoroutine {
            RMCharacterRepositoryImpl.get()
        }
            .map { charactersResponse ->
                mapper.transform(charactersResponse)
            }
    }
}