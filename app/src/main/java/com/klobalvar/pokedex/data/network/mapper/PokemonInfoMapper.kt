package com.klobalvar.pokedex.data.network.mapper

import com.klobalvar.pokedex.data.network.response.PokemonInfoResponse
import com.klobalvar.pokedex.model.PokemonInfo
import javax.inject.Inject

class PokemonInfoMapper @Inject constructor(
    private val statMapper: StatMapper,
    private val typeMapper: TypeMapper
) : Mapper<PokemonInfoResponse, PokemonInfo> {
    override fun fromRemote(model: PokemonInfoResponse): PokemonInfo =
        with(model) {
            PokemonInfo(
                id,
                name,
                height,
                weight,
                stats.map { statMapper.fromRemote(it) },
                types.map { typeMapper.fromRemote(it) }.toSet()
            )
        }
}