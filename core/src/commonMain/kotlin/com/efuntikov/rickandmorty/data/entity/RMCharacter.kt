package com.efuntikov.rickandmorty.data.entity

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class RMCharacterModel(
    @SerialName("id") var id: Int = -1,
    @SerialName("name") var name: String = "",
    @SerialName("status") var status: String = "",
    @SerialName("species") var species: String = "",
    @SerialName("type") var type: String = "",
    @SerialName("gender") var gender: String = "",
    @SerialName("origin") var origin: RMLocationResponse = RMLocationResponse(),
    @SerialName("location") var location: RMLocationResponse = RMLocationResponse(),
    @SerialName("image") var image: String = "",
    @SerialName("episode") var episode: List<String> = arrayListOf(),
    @SerialName("url") var url: String = "",
    @SerialName("created") var created: String = ""
)

enum class CharacterStatus(val status: String) {
    ALIVE("Alive"), DEAD("Dead"), UNKNOWN("Unknown")
}

data class RMCharacter(
    var id: Int = -1,
    var name: String = "",
    private var status: String = "",
    var species: String = "",
    var type: String = "",
    var gender: String = "",
    var origin: RMLocationResponse = RMLocationResponse(),
    var location: RMLocationResponse = RMLocationResponse(),
    var image: String = "",
    var episode: List<String> = arrayListOf(),
    var url: String = "",
    var created: String = ""
) {
    fun getStatus() = when(status) {
        CharacterStatus.ALIVE.status -> RMStatus.OK
        CharacterStatus.DEAD.status -> RMStatus.NOT_OK
        CharacterStatus.UNKNOWN.status -> RMStatus.UNKNOWN
        else -> RMStatus.UNKNOWN
    }
}