package com.klobalvar.pokedex.data.network.response

data class PokemonModel(
    val name: String,
    val url: String
) {

    fun getPokemonIndex(): Int? =
        try {
            url.split("/".toRegex()).dropLast(1).last().toInt()
        } catch (e: Exception) {
            null
        }

    fun getPokemonImageUrl(): String =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${getPokemonIndex() ?: 0}.png"
}