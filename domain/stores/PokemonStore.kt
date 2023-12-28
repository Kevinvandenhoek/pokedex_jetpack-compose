package nl.kevinvandenhoek.pokedex.domain.stores

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nl.kevinvandenhoek.pokedex.domain.models.Pokemon
import nl.kevinvandenhoek.pokedex.utilities.capitalized
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object PokemonStore {

    private var pokemon: List<Pokemon>? = null

    suspend fun getPokemon(id: Int): Pokemon? {
        return getPokemon().firstOrNull { it.id == id }
    }

    suspend fun getPokemon(): List<Pokemon> {
        val pokemon = pokemon
        if (pokemon is List<Pokemon>) {
            return pokemon
        }

        return withContext(Dispatchers.IO) {
            val url = URL("https://pokeapi.co/api/v2/pokemon")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            println("b")
            try {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                val jsonResponse = JSONObject(response)
                println("c")
                parsePokemonList(jsonResponse)
            } finally {
                connection.disconnect()
            }
        }
    }

    private fun parsePokemonList(jsonResponse: JSONObject): List<Pokemon> {
        val results = jsonResponse.getJSONArray("results")
        val pokemonList = mutableListOf<Pokemon>()

        for (i in 0 until results.length()) {
            val pokemonJson = results.getJSONObject(i)
            val pokemonName = pokemonJson.getString("name").capitalized()
            pokemonList.add(Pokemon(i + 1, pokemonName))
        }

        return pokemonList
    }
}