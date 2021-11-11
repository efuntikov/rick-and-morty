package com.efuntikov.rickandmorty.data

import com.efuntikov.rickandmorty.data.entity.RMCharacterModel
import com.efuntikov.rickandmorty.data.entity.RMPaginatedResponse
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString

class RMCharacterRequestImpl(private val apiUrl: String) : Request<RMPaginatedResponse<RMCharacterModel>>() {
    override suspend fun execute(): RMPaginatedResponse<RMCharacterModel> {

        return client
            .get<HttpStatement>(urlString = apiUrl)
            .execute { response ->
                json.decodeFromString(response.readText())
            }
    }
}