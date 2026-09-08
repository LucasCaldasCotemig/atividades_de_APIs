package com.lucao.rickandmorty.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lucao.rickandmorty.model.CharacterDto

@Composable
fun CharacterDetailScreen(
  character: CharacterDto,
  onBackClick: () -> Unit
  ) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(character.name) },
        navigationIcon = {
          IconButton(onClick = onBackClick) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
          }
        }
        )
    }
    ) { padding ->
    Column(
      modifier = Modifier
      .padding(padding)
      .fillMaxSize()
      .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
      AsyncImage(
        model = character.image,
        contentDescription = character.name,
        modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        )
      Text(text = character.name, style = MaterialTheme.typography.headlineSmall)
      Text(text = "Status: ${character.status}")
      Text(text = "Especie: ${character.species}")
      Text(text = "Genero: ${character.gender}")
      Text(text = "Origem: ${character.origin.name}")
      Text(text = "Ultima localizacao conhecida: ${character.location.name}")
      Text(text = "Aparece em ${character.episodes.size} episodio(s)")
    }
  }
}
