package com.efuntikov.rickandmorty.repository

import com.efuntikov.rickandmorty.data.RMApiRequestImpl
import com.efuntikov.rickandmorty.data.entity.RMApiResponse

object RMApiRepositoryImpl : Repository<RMApiResponse> {

    private var rmApi: RMApiResponse? = null

    override suspend fun get(): RMApiResponse {
        return rmApi?.run {
            return@run rmApi
        } ?: RMApiRequestImpl().execute().also {
            rmApi = it
        }
    }
}