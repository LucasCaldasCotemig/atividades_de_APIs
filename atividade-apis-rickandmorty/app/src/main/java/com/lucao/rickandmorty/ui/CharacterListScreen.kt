package com.lucao.rickandmorty.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lucao.rickandmorty.model.CharacterDto
import com.lucao.rickandmorty.viewmodel.CharacterListUiState
import com.lucao.rickandmorty.viewmodel.CharacterViewModel

@Composable
fun CharacterListScreen(
  viewModel: CharacterViewModel,
  onCharacterClick: (CharacterDto) -> Unit
  ) {
  val uiState by viewModel.uiState.collectAsState()
  var query by remember { mutableStateOf("") }

  Scaffold(
    topBar = { TopAppBar(title = { Text("Personagens") }) }
    ) { padding ->
    Column(modifier = Modifier.padding(padding).fillMaxSize()) {
      OutlinedTextField(
        value = query,
        onValueChange = {
          query = it
          viewModel.searchCharacters(it)
        },
        label = { Text("Buscar personagem") },
        modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        )

      when (val state = uiState) {
        is CharacterListUiState.Loading -> {
          Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
          }
        }
        is CharacterListUiState.Error -> {
          Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
              text = state.message,
              modifier = Modifier.padding(24.dp)
              )
          }
        }
        is CharacterListUiState.Success -> {
          LazyColumn {
            items(state.characters) { character ->
              CharacterRow(character = character, onClick = { onCharacterClick(character) })
              Divider()
            }
          }
        }
      }
    }
  }
}

@Composable
private fun CharacterRow(character: CharacterDto, onClick: () -> Unit) {
  Row(
    modifier = Modifier
    .fillMaxWidth()
    .clickable { onClick() }
    .padding(12.dp),
    verticalAlignment = Alignment.CenterVertically
    ) {
    AsyncImage(
      model = character.image,
      contentDescription = character.name,
      modifier = Modifier
      .size(56.dp)
      .clip(CircleShape)
      .background(MaterialTheme.colorScheme.surfaceVariant)
      )
    Spacer(modifier = Modifier.width(16.dp))
    Column {
      Text(text = character.name, style = MaterialTheme.typography.titleMedium)
      Text(text = "${character.species} . ${character.status}", style = MaterialTheme.typography.bodyMedium)
    }
  }
}
