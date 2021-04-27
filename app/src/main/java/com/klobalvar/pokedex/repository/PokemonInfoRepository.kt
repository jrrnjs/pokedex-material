package com.klobalvar.pokedex.repository

import com.klobalvar.pokedex.model.PokemonInfo


interface PokemonInfoRepository {

    suspend fun getPokemonInfo(
        name: String,
        onError: (String?) -> Unit
    ): PokemonInfo?
}