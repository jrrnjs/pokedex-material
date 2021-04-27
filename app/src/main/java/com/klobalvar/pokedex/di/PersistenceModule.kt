package com.klobalvar.pokedex.di

import android.app.Application
import androidx.room.Room
import com.klobalvar.pokedex.data.persistence.PokedexDatabase
import com.klobalvar.pokedex.data.persistence.PokemonDao
import com.klobalvar.pokedex.data.persistence.PokemonInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    fun providePokedexDatabase(application: Application): PokedexDatabase =
        Room.databaseBuilder(application, PokedexDatabase::class.java, "pokedex.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providePokemonDao(pokedexDatabase: PokedexDatabase): PokemonDao =
        pokedexDatabase.pokemonDao()

    @Provides
    fun providePokemonInfoDao(pokedexDatabase: PokedexDatabase): PokemonInfoDao =
        pokedexDatabase.pokemonInfoDao()
}