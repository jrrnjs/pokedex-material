package com.klobalvar.pokedex.data.persistence.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringSetTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): Set<String> {
        val type = object : TypeToken<Set<String>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromSet(value: Set<String>): String {
        return gson.toJson(value)
    }
}