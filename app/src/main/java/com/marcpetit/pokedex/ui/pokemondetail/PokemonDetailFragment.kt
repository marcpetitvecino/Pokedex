package com.marcpetit.pokedex.ui.pokemondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.marcpetit.pokedex.common.BaseFragment
import com.marcpetit.pokedex.common.visible
import com.marcpetit.pokedex.databinding.FragmentPokemonDetailBinding
import com.marcpetit.pokedex.domainmodel.Pokemon
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

private const val POKEMON_NAME = "pokemonName"

@AndroidEntryPoint
class PokemonDetailFragment : BaseFragment<FragmentPokemonDetailBinding>() {

    private val viewModel: PokemonDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        arguments?.getString(POKEMON_NAME)?.let {
            viewModel.dispatchEvent(PokemonDetailEvent.InitEvent(it))
        }
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, ::handleViewState)
    }

    private fun handleViewState(viewState: PokemonDetailViewState) {
        when (viewState) {
            is PokemonDetailViewState.ShowData -> loadData(viewState.pokemon)
            PokemonDetailViewState.Loading -> showLoading(true)
            PokemonDetailViewState.Error -> {
                showLoading(false)
                showErrorMessage("Error loading Pokemon detail")
            }
        }
    }

    private fun loadData(pokemon: Pokemon) {
        showLoading(false)
        with(binding) {
            Picasso.get().load(pokemon.sprites?.frontDefault).into(pokemonImage)
            pokemonName.text = "#${pokemon.id.toString().padStart(3, '0')} | ${pokemon.name}"
            context?.let {
                with(pokemon.types) {
                    pokemonType1.setImageDrawable(
                        ContextCompat.getDrawable(
                            it,
                            viewModel.getTypeDrawable(get(0).type?.name ?: "")
                        )
                    )
                    pokemonType1.contentDescription = get(0).type?.name
                    if (size > 1) {
                        pokemonType2.setImageDrawable(
                            ContextCompat.getDrawable(
                                it,
                                viewModel.getTypeDrawable(get(1).type?.name ?: "")
                            )
                        )
                        pokemonType2.contentDescription = get(1).type?.name
                    } else {
                        pokemonType2.visible(false)
                    }
                }
            }
        }
    }

    private fun showLoading(isVisible: Boolean) {
        binding.progressBar.visible(isVisible)
    }
}
