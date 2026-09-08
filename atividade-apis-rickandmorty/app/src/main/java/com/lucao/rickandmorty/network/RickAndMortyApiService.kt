package com.lucao.rickandmorty.network

import com.lucao.rickandmorty.model.CharacterDto
import com.lucao.rickandmorty.model.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {

  @GET("character")
  suspend fun getCharacters(
    @Query("name") name: String? = null,
    @Query("page") page: Int = 1
    ): CharacterResponseDto

  @GET("character/{id}")
  suspend fun getCharacterById(@Path("id") id: Int): CharacterDto
}
