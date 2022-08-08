package com.marcpetit.pokedex.ui.pokemondetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcpetit.pokedex.domainmodel.Pokemon
import com.marcpetit.pokedex.network.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

sealed class PokemonDetailViewState {
    class ShowData(val pokemon: Pokemon) : PokemonDetailViewState()
    object Loading : PokemonDetailViewState()
    object Error : PokemonDetailViewState()
}

sealed class PokemonDetailEvent {
    class InitEvent(val name: String) : PokemonDetailEvent()
}

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) :
    ViewModel() {

    private val mutableViewState = MutableLiveData<PokemonDetailViewState>()
    val viewState: LiveData<PokemonDetailViewState> get() = mutableViewState

    fun dispatchEvent(event: PokemonDetailEvent) {
        when (event) {
            is PokemonDetailEvent.InitEvent -> getPokemonDetail(event.name)
        }
    }
    private fun getPokemonDetail(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            mutableViewState.postValue(PokemonDetailViewState.Loading)
            try {
                mutableViewState.postValue(PokemonDetailViewState.ShowData(pokemonRepository.getPokemon(name)))
            } catch (e: Exception) {
                mutableViewState.postValue(PokemonDetailViewState.Error)
                Log.e("PokemonDetailViewModel", e.message.toString())
            }
        }
    }
}
