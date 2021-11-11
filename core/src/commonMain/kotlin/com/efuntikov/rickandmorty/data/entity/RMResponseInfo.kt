package com.efuntikov.rickandmorty.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RMResponseInfo(
    @SerialName("count") var count: Int = -1,
    @SerialName("pages") var pages: Int = -1,
    @SerialName("next") var next: String? = "",
    @SerialName("prev") var prev: String? = ""
)

@Serializable
data class RMPaginatedResponse<T>(
    @SerialName("info") var info: RMResponseInfo = RMResponseInfo(),
    @SerialName("results") var results: List<T> = arrayListOf()
)