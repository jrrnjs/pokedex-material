package com.klobalvar.pokedex.core.binding

import android.util.TypedValue
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fondesa.recyclerviewdivider.dividerBuilder
import com.klobalvar.pokedex.core.view.OnLoadMoreListener
import com.klobalvar.pokedex.ui.main.MainViewModel

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter(value = ["adapter", "item_space"], requireAll = false)
    fun initRecyclerView(view: RecyclerView, adapter: RecyclerView.Adapter<*>?, spaceSize: Int?) {
        view.adapter = adapter

        spaceSize?.let { size ->
            view.context.dividerBuilder()
                .size(size, TypedValue.COMPLEX_UNIT_DIP)
                .asSpace()
                .build()
                .addTo(view)
        }
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("submitList")
    fun bindList(view: RecyclerView, list: List<Any>?) {
        (view.adapter as? ListAdapter<Any, *>)?.submitList(list)
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("submitSet")
    fun bindSet(view: RecyclerView, list: Set<Any>?) {
        (view.adapter as? ListAdapter<Any, *>)?.submitList(list?.toList())
    }

    @JvmStatic
    @BindingAdapter("loadMore")
    fun bindLoadMore(view: RecyclerView, viewModel: MainViewModel) {
        OnLoadMoreListener(
            onLoadMore = { viewModel.getPokemonList() },
            isLoading = { viewModel.isLoading.value ?: false },
            isFinished = { viewModel.isFinished }
        ).apply {
            threshold = 10
        }.addTo(view)
    }
}