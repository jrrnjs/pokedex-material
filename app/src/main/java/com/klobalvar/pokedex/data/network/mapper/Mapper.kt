package com.klobalvar.pokedex.data.network.mapper

interface Mapper<in M, out E> {

    fun fromRemote(model: M): E
}