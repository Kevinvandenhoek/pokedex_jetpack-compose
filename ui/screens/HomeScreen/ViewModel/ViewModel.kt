package nl.kevinvandenhoek.pokedex.ui.screens.HomeScreen.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.kevinvandenhoek.pokedex.domain.models.Pokemon
import nl.kevinvandenhoek.pokedex.domain.stores.PokemonStore
import nl.kevinvandenhoek.pokedex.utilities.capitalized
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class HomeViewModel: ViewModel() {

    private val _pokemon: MutableStateFlow<List<Pokemon>> = MutableStateFlow(emptyList())
    val pokemon: StateFlow<List<Pokemon>> = _pokemon

    init {
        viewModelScope.launch {
            _pokemon.emit(PokemonStore.getPokemon())
        }
    }
}