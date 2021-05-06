package com.klobalvar.pokedex.ui.pokemon_profile

import androidx.lifecycle.*
import com.klobalvar.pokedex.core.base.BaseViewModel
import com.klobalvar.pokedex.model.Pokemon
import com.klobalvar.pokedex.model.PokemonInfo
import com.klobalvar.pokedex.repository.PokemonInfoRepository
import com.klobalvar.pokedex.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PokemonProfileViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val pokemonInfoRepository: PokemonInfoRepository
) : BaseViewModel() {

    private val _pokemonInfo: MutableLiveData<PokemonInfo> = MutableLiveData()
    val pokemonInfo: LiveData<PokemonInfo> = _pokemonInfo

    val types: LiveData<List<String>> = pokemonInfo.map { it.types.toList() }
    val typeToPokemonList: LiveData<List<Pair<String, List<Pokemon>>>> = types.asFlow().map { types ->
        types
            .map { type ->
                viewModelScope.async { pokemonRepository.getPokemonListByType(type) {} }
            }.awaitAll()
            .mapIndexed { index, list ->
                types[index] to list
            }
    }.asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)


    fun getPokemonInfo(name: String) {

        viewModelScope.launch {
            _isLoading.value = true
            withContext(Dispatchers.IO) {
                val result = pokemonInfoRepository.getPokemonInfo(name) {
                    it?.let { message ->
                        _errorMessage.postValue(message)
                    }
                }
                result?.let {
                    _pokemonInfo.postValue(result)
                }
            }
            _isLoading.value = false
        }
    }
}