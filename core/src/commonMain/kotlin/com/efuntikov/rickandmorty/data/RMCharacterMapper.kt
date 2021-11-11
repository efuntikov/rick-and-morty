package com.efuntikov.rickandmorty.data

import com.efuntikov.rickandmorty.data.entity.RMCharacter
import com.efuntikov.rickandmorty.data.entity.RMCharacterModel
import com.efuntikov.rickandmorty.data.entity.RMPaginatedResponse

class RMCharacterMapper : Mapper<RMPaginatedResponse<RMCharacterModel>, List<RMCharacter>> {
    override fun transform(response: RMPaginatedResponse<RMCharacterModel>): List<RMCharacter> {
        return response.results.map {
            RMCharacter(
                it.id, it.name, it.status, it.species, it.type,
                it.gender, it.origin, it.location, it.image, it.episode, it.url, it.created
            )
        }
    }
}