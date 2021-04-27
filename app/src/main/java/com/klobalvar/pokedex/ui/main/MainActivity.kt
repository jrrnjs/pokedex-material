package com.klobalvar.pokedex.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.klobalvar.pokedex.R
import com.klobalvar.pokedex.core.base.BindingActivity
import com.klobalvar.pokedex.core.extensions.initBinding
import com.klobalvar.pokedex.databinding.ActivityMainBinding
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)

        initBinding {
            lifecycleOwner = this@MainActivity
            adapter = PokemonAdapter()
            navigationClickListener = BackdropNavigationClickListener(
                binding.backLayer,
                binding.frontLayer,
                ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_menu_24),
                ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_close_24)
            )
            vm = viewModel
        }

        binding.backdrop.initBinding {
            lifecycleOwner = this@MainActivity
            adapter = FilterTypeAdapter { type ->
                binding.navigationClickListener?.hideBackdrop()
                if (type == "all") {
                    viewModel.getPokemonList()
                } else {
                    viewModel.getPokemonListByType(type)
                }
            }
        }
    }
}