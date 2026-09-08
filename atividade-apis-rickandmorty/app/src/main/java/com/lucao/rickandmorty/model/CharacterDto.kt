package com.lucao.rickandmorty.model

import com.google.gson.annotations.SerializedName

data class CharacterDto(
  val id: Int,
  val name: String,
  val status: String,
  val species: String,
  val type: String,
  val gender: String,
  val origin: LocationRefDto,
  val location: LocationRefDto,
  val image: String,
  @SerializedName("episode")
  val episodes: List<String>,
  @SerializedName("url")
  val apiUrl: String,
  val created: String
  )
