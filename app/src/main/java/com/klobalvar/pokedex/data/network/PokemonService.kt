package com.klobalvar.pokedex.data.network

import com.klobalvar.pokedex.data.network.response.PokemonInfoResponse
import com.klobalvar.pokedex.data.network.response.PokemonListByTypeResponse
import com.klobalvar.pokedex.data.network.response.PokemonListResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ApiResponse<PokemonListResponse>

    @GET("type/{type}")
    suspend fun fetchPokemonListByType(
        @Path("type") type: String
    ): ApiResponse<PokemonListByTypeResponse>

    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(
        @Path("name") name: String
    ): ApiResponse<PokemonInfoResponse>
}