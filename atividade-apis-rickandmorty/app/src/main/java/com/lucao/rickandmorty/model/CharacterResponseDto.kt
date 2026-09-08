package com.lucao.rickandmorty.model

data class CharacterResponseDto(
  val info: InfoDto,
  val results: List<CharacterDto>
    )
