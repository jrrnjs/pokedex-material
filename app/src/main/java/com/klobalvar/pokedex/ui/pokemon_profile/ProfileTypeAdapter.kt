package com.klobalvar.pokedex.ui.pokemon_profile

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klobalvar.pokedex.R
import com.klobalvar.pokedex.core.PokemonColorUtil
import com.klobalvar.pokedex.core.extensions.binding
import com.klobalvar.pokedex.databinding.ItemProfileTypeBinding

class ProfileTypeAdapter : ListAdapter<String, ProfileTypeAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = parent.binding<ItemProfileTypeBinding>(R.layout.item_profile_type)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val typeColorRes = PokemonColorUtil.getColorByType(getItem(position))
        val typeColor = ContextCompat.getColor(holder.itemView.context, typeColorRes)

        holder.binding.run {
            pokemonName.setTextColor(typeColor)
            type = getItem(position)
            executePendingBindings()
        }
    }


    class ViewHolder(val binding: ItemProfileTypeBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem.hashCode() == newItem.hashCode()
        }
    }
}