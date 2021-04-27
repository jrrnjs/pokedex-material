package com.klobalvar.pokedex.data.network.response

data class StatModel(
    val stat: StatNameModel,
    val base_stat: Int
) {
    data class StatNameModel(
        val name: String
    )
}
