package com.klobalvar.pokedex.data.network.mapper

import com.klobalvar.pokedex.data.network.response.PokemonModel
import com.klobalvar.pokedex.model.Pokemon
import javax.inject.Inject

class PokemonMapper @Inject constructor() : Mapper<PokemonModel, Pokemon> {

    override fun fromRemote(model: PokemonModel): Pokemon =
        with(model) {
            Pokemon(
                getPokemonIndex() ?: 0,
                name,
                getPokemonImageUrl()
            )
        }
}