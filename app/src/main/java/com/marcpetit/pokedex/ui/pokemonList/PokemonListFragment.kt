package com.marcpetit.pokedex.ui.pokemonList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcpetit.pokedex.R
import com.marcpetit.pokedex.common.BaseFragment
import com.marcpetit.pokedex.common.visible
import com.marcpetit.pokedex.databinding.FragmentPokemonListBinding
import com.marcpetit.pokedex.domainmodel.PokemonListDisplay
import dagger.hilt.android.AndroidEntryPoint

private const val POKEMON_NAME = "pokemonName"

@AndroidEntryPoint
class PokemonListFragment : BaseFragment<FragmentPokemonListBinding>() {

    private val viewModel: PokemonListViewModel by viewModels()

    private var isLoading: Boolean = false
    private var offset = 0

    private lateinit var adapter: PokemonListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dispatchEvent(PokemonListEvent.InitEvent)
        adapter = PokemonListAdapter(::onPokemonClick)
        binding.pokemonRecyclerview.adapter = adapter
        setListeners()
        initObservers()
    }

    private fun setListeners() {
        binding.pokemonRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                with(binding.pokemonRecyclerview.layoutManager as LinearLayoutManager) {
                    if (findLastVisibleItemPosition() + 5 >= itemCount) {
                        if (!isLoading) {
                            isLoading = true
                            offset += 20
                            viewModel.dispatchEvent(PokemonListEvent.LoadMoreEvent(offset))
                        }
                    }
                }
            }
        })
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, ::handleViewState)
    }

    private fun handleViewState(viewState: PokemonListViewState) {
        when (viewState) {
            PokemonListViewState.Error -> {
                showLoading(false)
                showErrorMessage("Error loading Pokemon list")
            }
            PokemonListViewState.Loading -> showLoading(true)
            is PokemonListViewState.ShowData -> loadData(viewState.list)
        }
    }

    private fun showLoading(isVisible: Boolean) {
        isLoading = true
        binding.progressBar.visible(isVisible)
    }

    private fun loadData(pokemonList: List<PokemonListDisplay>) {
        isLoading = false
        adapter.submitList(pokemonList.toMutableList())
    }

    private fun onPokemonClick(pokemonName: String) {
        findNavController().navigate(
            R.id.action_pokemonListFragment_to_pokemonDetailFragment,
            Bundle().apply {
                putString(POKEMON_NAME, pokemonName)
            }
        )
    }
}
