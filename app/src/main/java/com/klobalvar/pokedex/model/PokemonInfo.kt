package com.klobalvar.pokedex.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class PokemonInfo(
    @PrimaryKey val index: Int,
    val name: String,
    val height: Float,
    val weight: Float,
    val stats: List<Stat>,
    val types: Set<String>
) : Parcelable {

    fun hp(): Int = stats.firstOrNull { it.name == "hp" }?.value ?: 0
    fun attack(): Int = stats.firstOrNull { it.name == "attack" }?.value ?: 0
    fun defense(): Int = stats.firstOrNull { it.name == "defense" }?.value ?: 0
    fun speed(): Int = stats.firstOrNull { it.name == "speed" }?.value ?: 0
    fun specialAttack(): Int = stats.firstOrNull { it.name == "special-attack" }?.value ?: 0
    fun specialDefense(): Int = stats.firstOrNull { it.name == "special-defense" }?.value ?: 0

    fun specialAttackLabel(): String = "${specialAttack()}/$statMaxValue"
    fun specialDefenseLabel(): String = "${specialDefense()}/$statMaxValue"

    companion object {
        const val statMaxValue = 300
    }
}