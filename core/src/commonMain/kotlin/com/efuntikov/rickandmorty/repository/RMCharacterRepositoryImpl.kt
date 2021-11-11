package com.efuntikov.rickandmorty.repository

import com.efuntikov.rickandmorty.data.RMCharacterRequestImpl
import com.efuntikov.rickandmorty.data.entity.*

object RMCharacterRepositoryImpl : Repository<RMPaginatedResponse<RMCharacterModel>> {

    private var characters: RMPaginatedResponse<RMCharacterModel>? = null

    override suspend fun get(): RMPaginatedResponse<RMCharacterModel> {
        return characters?.run {
            return@run characters
        } ?: RMCharacterRequestImpl(RMApiRepositoryImpl.get().characters).execute().also {
            characters = it
        }
    }
}