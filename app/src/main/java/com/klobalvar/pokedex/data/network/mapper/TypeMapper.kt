package com.klobalvar.pokedex.data.network.mapper

import com.klobalvar.pokedex.data.network.response.TypeModel
import javax.inject.Inject

class TypeMapper @Inject constructor() : Mapper<TypeModel, String> {
    override fun fromRemote(model: TypeModel): String =
        model.type.name
}