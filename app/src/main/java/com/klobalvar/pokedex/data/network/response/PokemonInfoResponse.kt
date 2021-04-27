package com.klobalvar.pokedex.data.network.response

data class PokemonInfoResponse(
    val id: Int,
    val name: String,
    val height: Float,
    val weight: Float,
    val stats: List<StatModel>,
    val types: List<TypeModel>
)