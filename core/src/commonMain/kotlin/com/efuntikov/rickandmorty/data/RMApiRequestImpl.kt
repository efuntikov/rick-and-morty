package com.efuntikov.rickandmorty.data

import com.efuntikov.rickandmorty.data.entity.RMApiResponse
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class RMApiRequestImpl : Request<RMApiResponse>() {

    companion object {
        private const val API_URL = "https://rickandmortyapi.com/api"
    }

    override suspend fun execute(): RMApiResponse {
        return json.decodeFromString(
            client
                .get<HttpStatement>(urlString = API_URL)
                .execute()
                .readText()
        )
    }
}