package nl.kevinvandenhoek.pokedex.utilities

fun String.capitalized(): String {
    if (this.isEmpty()) return this
    return this[0].uppercase() + this.substring(1)
}
