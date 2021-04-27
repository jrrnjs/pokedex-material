package com.klobalvar.pokedex.di

import com.klobalvar.pokedex.repository.PokemonInfoRepository
import com.klobalvar.pokedex.repository.PokemonInfoRepositoryImpl
import com.klobalvar.pokedex.repository.PokemonRepository
import com.klobalvar.pokedex.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPokemonRepository(repository: PokemonRepositoryImpl): PokemonRepository

    @Binds
    abstract fun bindPokemonInfoRepository(repository: PokemonInfoRepositoryImpl): PokemonInfoRepository
}