package com.klobalvar.pokedex.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.klobalvar.pokedex.data.persistence.converter.StatListTypeConverter
import com.klobalvar.pokedex.data.persistence.converter.StringSetTypeConverter
import com.klobalvar.pokedex.model.Pokemon
import com.klobalvar.pokedex.model.PokemonInfo

@Database(
    entities = [
        Pokemon::class,
        PokemonInfo::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    value = [
        StringSetTypeConverter::class,
        StatListTypeConverter::class
    ]
)
abstract class PokedexDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonInfoDao(): PokemonInfoDao
}