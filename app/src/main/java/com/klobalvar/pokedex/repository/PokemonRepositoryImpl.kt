package com.klobalvar.pokedex.repository

import com.klobalvar.pokedex.data.network.PokemonService
import com.klobalvar.pokedex.data.network.mapper.PokemonMapper
import com.klobalvar.pokedex.data.persistence.PokemonDao
import com.klobalvar.pokedex.model.Pokemon
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonDao: PokemonDao,
    private val pokemonMapper: PokemonMapper
) : PokemonRepository {

    override suspend fun getPokemonList(
        page: Int,
        onError: (String?) -> Unit
    ): List<Pokemon> {
        val start = 1
        val count = (page + 1) * ROWS

        var pokemonList = pokemonDao.getPokemonList(start, count)

        if (pokemonList.size < count) {
            pokemonService.fetchPokemonList(ROWS, page * ROWS)
                .suspendOnSuccess {
                    data?.let { response ->
                        pokemonDao.upsertPokemonList(
                            response.results.map { model ->
                                pokemonMapper.fromRemote(model)
                            }
                        )
                        pokemonList = pokemonDao.getPokemonList(start, count)
                    }
                }.onError {
                    onError(message())
                }.onException {
                    onError(message())
                }
        }

        return pokemonList
    }

    override suspend fun getPokemonListByType(
        type: String,
        onError: (String?) -> Unit
    ): List<Pokemon> {
        var pokemonList = pokemonDao.getPokemonListByType(type)

        if (pokemonList.isEmpty()) {
            pokemonService.fetchPokemonListByType(type)
                .suspendOnSuccess {
                    data?.let { response ->
                        pokemonDao.upsertPokemonList(
                            response.pokemon.map { pokemonByTypeResponse ->
                                pokemonMapper.fromRemote(pokemonByTypeResponse.pokemon).apply {
                                    this.type.add(response.name)
                                }
                            }
                        )
                        pokemonList = pokemonDao.getPokemonListByType(type)
                    }
                }.onError {
                    onError(message())
                }.onException {
                    onError(message())
                }
        }

        return pokemonList
    }

    companion object {
        const val ROWS = 30
    }
}