package com.klobalvar.pokedex.ui.pokemon_profile

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klobalvar.pokedex.R
import com.klobalvar.pokedex.core.extensions.binding
import com.klobalvar.pokedex.databinding.ItemTypePokemonBinding
import com.klobalvar.pokedex.databinding.LayoutPopupPokemonBinding
import com.klobalvar.pokedex.model.Pokemon

class TypePokemonAdapter(
    private val layoutPopupPokemonBinding: LayoutPopupPokemonBinding
) : ListAdapter<Pokemon, TypePokemonAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = parent.binding<ItemTypePokemonBinding>(R.layout.item_type_pokemon)
        return ViewHolder(binding).apply {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { p ->
                    p != RecyclerView.NO_POSITION
                } ?: return@setOnClickListener

                if (layoutPopupPokemonBinding.isShow == false) {
                    layoutPopupPokemonBinding.isShow = true
                    layoutPopupPokemonBinding.pokemon = getItem(position)
                    layoutPopupPokemonBinding.root.setOnClickListener {
                        layoutPopupPokemonBinding.isShow = false
                        binding.transformationLayout.finishTransform()
                    }
                    layoutPopupPokemonBinding.executePendingBindings()
                    binding.transformationLayout.bindTargetView(layoutPopupPokemonBinding.root)
                    binding.transformationLayout.startTransform()
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.run {
            pokemon = getItem(position)
            executePendingBindings()
        }
    }

    class ViewHolder(val binding: ItemTypePokemonBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {

        private val diffUtil = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem
        }
    }
}