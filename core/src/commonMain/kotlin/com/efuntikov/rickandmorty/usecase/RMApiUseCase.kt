package com.efuntikov.rickandmorty.usecase

import com.badoo.reaktive.single.Single
import com.efuntikov.rickandmorty.data.entity.RMApi

interface RMApiUseCase : UseCase {
    fun getApi(): Single<RMApi>
}