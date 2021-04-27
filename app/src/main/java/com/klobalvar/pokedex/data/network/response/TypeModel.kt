package com.klobalvar.pokedex.data.network.response

data class TypeModel(
    val type: TypeNameModel
) {
    data class TypeNameModel(
        val name: String
    )
}