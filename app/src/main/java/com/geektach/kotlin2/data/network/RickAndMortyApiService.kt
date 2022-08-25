package com.geektach.kotlin2.data.network

import retrofit2.http.GET

interface RickAndMortyApiService {

    @GET("character")
    fun getAllCharacter(): MainResultDto<CharacterDto>
}