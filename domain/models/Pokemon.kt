package nl.kevinvandenhoek.pokedex.domain.models

data class Pokemon(
    val id: Int,
    val name: String
) {
    val thumbnail: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    val image: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}