package com.efuntikov.rickandmorty.data

import com.efuntikov.rickandmorty.data.entity.RMApi
import com.efuntikov.rickandmorty.data.entity.RMApiResponse

class ApisMapper: Mapper<RMApiResponse, RMApi> {

    override fun transform(response: RMApiResponse): RMApi {
        return RMApi(response.characters, response.locations, response.episodes)
    }
}