package com.klobalvar.pokedex.data.persistence.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.klobalvar.pokedex.model.Stat

class StatListTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<Stat> {
        val type = object : TypeToken<List<Stat>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromSet(value: List<Stat>): String {
        return gson.toJson(value)
    }
}