package com.klobalvar.pokedex.repository

import com.klobalvar.pokedex.data.network.PokemonService
import com.klobalvar.pokedex.data.network.mapper.PokemonInfoMapper
import com.klobalvar.pokedex.data.persistence.PokemonInfoDao
import com.klobalvar.pokedex.model.PokemonInfo
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import javax.inject.Inject

class PokemonInfoRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonInfoDao: PokemonInfoDao,
    private val pokemonInfoMapper: PokemonInfoMapper
) : PokemonInfoRepository {

    override suspend fun getPokemonInfo(name: String, onError: (String?) -> Unit): PokemonInfo? {
        var pokemonInfo = pokemonInfoDao.getPokemonInfo(name)

        if (pokemonInfo == null) {
            pokemonService.fetchPokemonInfo(name)
                .suspendOnSuccess {
                    data?.let { response ->
                        pokemonInfoDao.insertPokemonInfo(
                            pokemonInfoMapper.fromRemote(response)
                        )
                        pokemonInfo = pokemonInfoDao.getPokemonInfo(name)
                    }
                }.onError {
                    onError(message())
                }.onException {
                    onError(message())
                }
        }

        return pokemonInfo
    }
}