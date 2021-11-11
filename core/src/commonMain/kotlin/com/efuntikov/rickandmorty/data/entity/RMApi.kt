package com.efuntikov.rickandmorty.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RMApiResponse(
    @SerialName("characters") var characters: String = "",
    @SerialName("locations") var locations: String = "",
    @SerialName("episodes") var episodes: String = "",
)

data class RMApi(
    var characters: String = "",
    var locations: String = "",
    var episodes: String = "",
)