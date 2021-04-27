package com.klobalvar.pokedex.ui.pokemon_profile

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klobalvar.pokedex.R
import com.klobalvar.pokedex.core.PokemonColorUtil
import com.klobalvar.pokedex.core.extensions.binding
import com.klobalvar.pokedex.core.extensions.initBinding
import com.klobalvar.pokedex.databinding.ItemTypePokemonListBinding
import com.klobalvar.pokedex.databinding.LayoutPopupPokemonBinding
import com.klobalvar.pokedex.model.Pokemon

class TypePokemonListAdapter(
    private val layoutPopupPokemonBinding: LayoutPopupPokemonBinding
) : ListAdapter<Pair<String, List<Pokemon>>, TypePokemonListAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = parent.binding<ItemTypePokemonListBinding>(R.layout.item_type_pokemon_list)
        binding.initBinding {
            adapter = TypePokemonAdapter(layoutPopupPokemonBinding)
            executePendingBindings()
        }
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pair = getItem(position)
        val typeColorRes = PokemonColorUtil.getColorByType(pair.first)
        val typeColor = ContextCompat.getColor(holder.itemView.context, typeColorRes)

        holder.binding.run {
            this.type.setTextColor(typeColor)

            pokemonType = "${pair.first.capitalize()} Pokemons"
            pokemonList = pair.second

            executePendingBindings()
        }
    }

    class ViewHolder(val binding: ItemTypePokemonListBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {

        private val diffUtil = object : DiffUtil.ItemCallback<Pair<String, List<Pokemon>>>() {
            override fun areItemsTheSame(oldItem: Pair<String, List<Pokemon>>, newItem: Pair<String, List<Pokemon>>): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Pair<String, List<Pokemon>>, newItem: Pair<String, List<Pokemon>>): Boolean =
                oldItem.second.size == newItem.second.size
        }
    }
}