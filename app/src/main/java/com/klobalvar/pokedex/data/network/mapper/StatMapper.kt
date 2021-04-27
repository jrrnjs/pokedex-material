package com.klobalvar.pokedex.data.network.mapper

import com.klobalvar.pokedex.data.network.response.StatModel
import com.klobalvar.pokedex.model.Stat
import javax.inject.Inject

class StatMapper @Inject constructor() : Mapper<StatModel, Stat> {
    override fun fromRemote(model: StatModel): Stat =
        with(model) {
            Stat(
                stat.name,
                base_stat
            )
        }
}