package nl.kevinvandenhoek.pokedex.domain.models

data class PokemonDetails(
    val types: List<String>
    val baseExp: Int
    val weight: Int
    val height: Int
    val abilities: List<PokemonAbility>
)