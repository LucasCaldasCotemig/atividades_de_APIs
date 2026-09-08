package com.lucao.rickandmorty.viewmodel

import com.lucao.rickandmorty.model.CharacterDto

sealed interface CharacterListUiState {
  data object Loading : CharacterListUiState
  data class Success(val characters: List<CharacterDto>) : CharacterListUiState
  data class Error(val message: String) : CharacterListUiState
}
