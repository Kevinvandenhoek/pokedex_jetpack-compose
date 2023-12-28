package nl.kevinvandenhoek.pokedex.ui.screens.HomeScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import nl.kevinvandenhoek.pokedex.domain.models.Pokemon
import nl.kevinvandenhoek.pokedex.ui.composables.PokemonCell
import nl.kevinvandenhoek.pokedex.ui.screens.HomeScreen.ViewModel.HomeViewModel
import nl.kevinvandenhoek.pokedex.utilities.withShadow

@Composable
fun HomeScreen(viewModel: HomeViewModel, onSelect: (Pokemon) -> Unit) {
    val pokemon = viewModel.pokemon.collectAsState().value


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier
            .statusBarsPadding()
    ) {
        items(pokemon) { pokemon ->
            Surface(
                onClick = { onSelect(pokemon) }
            ) {
                PokemonCell(pokemon)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable()
fun HomeScreenPreview() {
    HomeScreen(viewModel = HomeViewModel(), onSelect = { })
}
