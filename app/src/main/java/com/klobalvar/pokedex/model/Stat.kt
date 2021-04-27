package com.klobalvar.pokedex.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stat(
    val name: String,
    val value: Int
) : Parcelable