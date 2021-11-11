package com.efuntikov.rickandmorty.data

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

abstract class Request<T> {

    protected val json: Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    protected val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

//    protected fun HttpRequestBuilder.apiUrl(path: String? = null) {
//        header(HttpHeaders.CacheControl, "no-cache")
//        url {
//            takeFrom(hostUrl).parameters.append("apiKey", key)
//            path?.let {
//                encodedPath = it
//            }
//        }
//    }

    abstract suspend fun execute(): T
}