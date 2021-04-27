package com.klobalvar.pokedex.data.persistence

import androidx.room.*
import com.klobalvar.pokedex.model.Pokemon

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokemonList(pokemonList: List<Pokemon>): List<Long>

    @Update
    suspend fun updatePokemonList(pokemonList: List<Pokemon>)

    @Transaction
    suspend fun upsertPokemonList(pokemonList: List<Pokemon>) {
        val insertResult = insertPokemonList(pokemonList)

        val conflictNew = mutableListOf<Pokemon>()
        insertResult.forEachIndexed { index, result ->
            if (result == -1L) {
                conflictNew.add(pokemonList[index])
            }
        }

        val conflictIndices = conflictNew.map { it.index }
        val conflictOld = getPokemonListByIndices(conflictIndices)


        conflictOld.forEachIndexed { index, pokemon ->
            if (index < conflictNew.size) {
                pokemon.type.addAll(conflictNew[index].type)
            }
        }

        if (conflictOld.isNotEmpty()) {
            updatePokemonList(conflictOld)
        }
    }

    @Query("SELECT * FROM Pokemon WHERE `index` >= :start AND `index` < (:start+:count) ORDER BY `index` ASC")
    suspend fun getPokemonList(start: Int, count: Int): List<Pokemon>

    @Query("SELECT * FROM pokemon WHERE type LIKE '%' || :type || '%' ORDER BY `index` ASC")
    suspend fun getPokemonListByType(type: String): List<Pokemon>

    @Query("SELECT * FROM pokemon WHERE `index` IN (:indices) ORDER BY `index` ASC")
    suspend fun getPokemonListByIndices(indices: List<Int>): List<Pokemon>
}