package com.klobalvar.pokedex.repository

import com.klobalvar.pokedex.model.Pokemon

interface PokemonRepository {

    suspend fun getPokemonList(
        page: Int,
        onError: (String?) -> Unit
    ): List<Pokemon>

    suspend fun getPokemonListByType(
        type: String,
        onError: (String?) -> Unit
    ): List<Pokemon>
}