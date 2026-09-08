package com.lucao.rickandmorty.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucao.rickandmorty.model.CharacterDto
import com.lucao.rickandmorty.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CharacterViewModel : ViewModel() {

  private val _uiState = MutableStateFlow<CharacterListUiState>(CharacterListUiState.Loading)
  val uiState: StateFlow<CharacterListUiState> = _uiState.asStateFlow()

  private val _selectedCharacter = MutableStateFlow<CharacterDto?>(null)
  val selectedCharacter: StateFlow<CharacterDto?> = _selectedCharacter.asStateFlow()

  init {
    searchCharacters("")
  }

  fun searchCharacters(query: String) {
    viewModelScope.launch {
      _uiState.value = CharacterListUiState.Loading
      try {
        val response = RetrofitInstance.api.getCharacters(name = query.ifBlank { null })
        _uiState.value = CharacterListUiState.Success(response.results)
      } catch (e: HttpException) {
        _uiState.value = CharacterListUiState.Error("Nenhum personagem encontrado para \"$query\".")
      } catch (e: IOException) {
        _uiState.value = CharacterListUiState.Error("Falha de conexao. Verifique sua internet e tente novamente.")
      } catch (e: Exception) {
        _uiState.value = CharacterListUiState.Error("Ocorreu um erro inesperado. Tente novamente.")
      }
    }
  }

  fun selectCharacter(character: CharacterDto) {
    _selectedCharacter.value = character
  }

  fun clearSelection() {
    _selectedCharacter.value = null
  }
}
