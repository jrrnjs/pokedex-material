package com.klobalvar.pokedex.data.network.response

data class PokemonListByTypeResponse(
    val name: String,
    val pokemon: List<PokemonByTypeResponse>
) {
    data class PokemonByTypeResponse(
        val pokemon: PokemonModel
    )
}