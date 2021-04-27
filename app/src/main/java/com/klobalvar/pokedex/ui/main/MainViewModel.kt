package com.klobalvar.pokedex.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klobalvar.pokedex.core.base.BaseViewModel
import com.klobalvar.pokedex.model.Pokemon
import com.klobalvar.pokedex.repository.PokemonRepository
import com.klobalvar.pokedex.repository.PokemonRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : BaseViewModel() {

    private var page = 0
    var isFinished: Boolean = false
        private set
    private val _pokemonList: MutableLiveData<List<Pokemon>> = MutableLiveData()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        viewModelScope.launch {
            _isLoading.value = true
            withContext(Dispatchers.IO) {
                val result = pokemonRepository.getPokemonList(page++) {
                    page--
                    it?.let { message ->
                        _errorMessage.postValue(message)
                    }
                }
                _pokemonList.postValue(result)
                isFinished = page * PokemonRepositoryImpl.ROWS > result.size
            }
            _isLoading.value = false
        }
    }

    fun getPokemonListByType(type: String) {
        viewModelScope.launch {
            _isLoading.value = true
            withContext(Dispatchers.IO) {
                val result = pokemonRepository.getPokemonListByType(type) {
                    it?.let { message ->
                        _errorMessage.postValue(message)
                    }
                }
                _pokemonList.postValue(result)
            }
            _isLoading.value = false
            isFinished = true
        }
    }
}