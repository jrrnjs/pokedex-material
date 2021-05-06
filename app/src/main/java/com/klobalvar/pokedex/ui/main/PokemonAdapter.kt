package com.klobalvar.pokedex.ui.main

import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klobalvar.pokedex.R
import com.klobalvar.pokedex.core.extensions.binding
import com.klobalvar.pokedex.databinding.ItemPokemonBinding
import com.klobalvar.pokedex.model.Pokemon
import com.klobalvar.pokedex.ui.pokemon_profile.PokemonProfileActivity
import com.skydoves.transformationlayout.TransformationCompat

class PokemonAdapter : ListAdapter<Pokemon, PokemonAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = parent.binding<ItemPokemonBinding>(R.layout.item_pokemon)
        return ViewHolder(binding).apply {
            itemView.setOnClickListener { v ->
                val position = bindingAdapterPosition.takeIf { p ->
                    p != RecyclerView.NO_POSITION
                } ?: return@setOnClickListener

                val intent = Intent(v.context, PokemonProfileActivity::class.java)
                    .putExtra("pokemon", getItem(position))

                TransformationCompat.startActivity(binding.transformationLayout, intent)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.run {
            pokemon = getItem(position)
            executePendingBindings()
        }
    }

    class ViewHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {

        private val diffUtil = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem
        }
    }
}