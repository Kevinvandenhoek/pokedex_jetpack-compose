package nl.kevinvandenhoek.pokedex.ui.screens.PokemonDetailScreen.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nl.kevinvandenhoek.pokedex.domain.models.Pokemon
import nl.kevinvandenhoek.pokedex.domain.stores.PokemonStore

class PokemonDetailViewModel(id: Int): ViewModel() {

    val pokemonId: Int = id

    private val _pokemon: MutableStateFlow<Pokemon?> = MutableStateFlow(null)
    val pokemon: StateFlow<Pokemon?> = _pokemon

    private val _details: MutableStateFlow<PokemonDetails?> = MutableStateFlow(null)
    val details: StateFlow<PokemonDetails?> = _details

    init {
        viewModelScope.launch {
            _pokemon.emit(PokemonStore.getPokemon(id))
        }
    }
}