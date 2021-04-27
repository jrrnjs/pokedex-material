package com.klobalvar.pokedex.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Pokemon(
    @PrimaryKey val index: Int,
    val name: String,
    val imageUrl: String,
    var type: MutableSet<String> = hashSetOf()
) : Parcelable {

    fun indexString(): String = "No.$index"
}