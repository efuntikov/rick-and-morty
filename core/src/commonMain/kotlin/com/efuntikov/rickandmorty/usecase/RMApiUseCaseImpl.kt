package com.efuntikov.rickandmorty.usecase

import com.badoo.reaktive.scheduler.createMainScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.map
import com.badoo.reaktive.single.observeOn
import com.efuntikov.rickandmorty.CoroutineExtensions
import com.efuntikov.rickandmorty.data.ApisMapper
import com.efuntikov.rickandmorty.data.entity.RMApi
import com.efuntikov.rickandmorty.repository.RMApiRepositoryImpl

object RMApiUseCaseImpl : RMApiUseCase {

    private val mapper: ApisMapper = ApisMapper()

    override fun getApi(): Single<RMApi> {
        return CoroutineExtensions.singleFromCoroutine { RMApiRepositoryImpl.get() }
            .map { response -> return@map mapper.transform(response) }
            .observeOn(createMainScheduler())
    }
}