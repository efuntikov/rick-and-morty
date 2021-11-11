package com.efuntikov.rickandmorty.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RMEpisodeResponse(
    @SerialName("id") var id: Int = -1,
    @SerialName("name") var name: String = "",
    @SerialName("air_date") val airDate: String = "",
    @SerialName("episode") var episode: String = "",
    @SerialName("characters") var characters: List<String> = arrayListOf(),
    @SerialName("url") var url: String = "",
    @SerialName("created") var created: String = ""
)

data class RMEpisode(
    var id: Int = -1,
    var name: String = "",
    var airDate: String = "",
    var episode: String = "",
    var characters: List<String> = arrayListOf(),
    var url: String = "",
    var created: String = ""
)