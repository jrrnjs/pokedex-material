package com.klobalvar.pokedex.ui.pokemon_profile

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import com.klobalvar.pokedex.R
import com.klobalvar.pokedex.core.base.BindingActivity
import com.klobalvar.pokedex.core.extensions.initBinding
import com.klobalvar.pokedex.databinding.ActivityPokemonProfileBinding
import com.klobalvar.pokedex.model.Pokemon
import com.skydoves.transformationlayout.onTransformationEndContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonProfileActivity : BindingActivity<ActivityPokemonProfileBinding>(R.layout.activity_pokemon_profile) {

    private val viewModel by viewModels<PokemonProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationEndContainer(intent.getParcelableExtra("TransformationParams"))
        super.onCreate(savedInstanceState)

        initBinding {
            lifecycleOwner = this@PokemonProfileActivity
            vm = viewModel
            typeAdapter = ProfileTypeAdapter()
            typePokemonListAdapter = TypePokemonListAdapter(popupPokemon)

            pokemon = (intent.getParcelableExtra("pokemon") as? Pokemon)?.also {
                viewModel.getPokemonInfo(it.name)
            }
        }

        binding.popupPokemon.initBinding {
            adapter = ProfileTypeAdapter()
            isShow = false
        }

        binding.run {
            progressAttack.labelTypefaceObject = ResourcesCompat.getFont(this@PokemonProfileActivity, R.font.rubik_medium)
            progressDefense.labelTypefaceObject = ResourcesCompat.getFont(this@PokemonProfileActivity, R.font.rubik_medium)
        }
    }
}