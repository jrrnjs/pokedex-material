package com.klobalvar.pokedex.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klobalvar.pokedex.R
import com.klobalvar.pokedex.core.extensions.binding
import com.klobalvar.pokedex.databinding.ItemFilterTypeBinding

class FilterTypeAdapter(private val onClick: (String) -> Unit) : ListAdapter<String, FilterTypeAdapter.ViewHolder>(diffUtil) {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        submitList(recyclerView.context.resources.getStringArray(R.array.pokemon_types).asList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = parent.binding<ItemFilterTypeBinding>(R.layout.item_filter_type)
        return ViewHolder(binding).apply {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { p ->
                    p != RecyclerView.NO_POSITION
                } ?: return@setOnClickListener

                onClick(getItem(position).toLowerCase())
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.run {
            type = getItem(position)
            executePendingBindings()
        }
    }

    class ViewHolder(val binding: ItemFilterTypeBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem.hashCode() == newItem.hashCode()
        }
    }
}