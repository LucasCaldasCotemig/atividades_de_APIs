package com.lucao.rickandmorty.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucao.rickandmorty.viewmodel.CharacterViewModel

private const val ROUTE_LIST = "list"
private const val ROUTE_DETAIL = "detail"

@Composable
fun AppNavigation() {
  val navController = rememberNavController()
  val viewModel: CharacterViewModel = viewModel()
  val selectedCharacter by viewModel.selectedCharacter.collectAsState()

  NavHost(navController = navController, startDestination = ROUTE_LIST) {
    composable(ROUTE_LIST) {
      CharacterListScreen(
        viewModel = viewModel,
        onCharacterClick = { character ->
          viewModel.selectCharacter(character)
          navController.navigate(ROUTE_DETAIL)
        }
        )
    }
    composable(ROUTE_DETAIL) {
      selectedCharacter?.let { character ->
        CharacterDetailScreen(
          character = character,
          onBackClick = {
            viewModel.clearSelection()
            navController.popBackStack()
          }
          )
      }
    }
  }
}
