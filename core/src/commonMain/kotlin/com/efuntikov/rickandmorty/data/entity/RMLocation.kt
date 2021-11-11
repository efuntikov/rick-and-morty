package com.efuntikov.rickandmorty.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RMLocationResponse(
    @SerialName("id") var id: Int = -1,
    @SerialName("name") var name: String = "",
    @SerialName("type") var type: String = "",
    @SerialName("dimension") var dimension: String = "",
    @SerialName("residents") var residents: List<String> = arrayListOf(),
    @SerialName("url") var url: String = "",
    @SerialName("created") var created: String = ""
)

data class RMLocation(
    var id: Int = -1,
    var name: String = "",
    var type: String = "",
    var dimension: String = "",
    var residents: List<String> = arrayListOf(),
    var url: String = "",
    var created: String = ""
)