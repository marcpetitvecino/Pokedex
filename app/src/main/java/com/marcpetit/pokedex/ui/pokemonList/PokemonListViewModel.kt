package com.marcpetit.pokedex.ui.pokemonList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcpetit.pokedex.domainmodel.PokemonListDisplay
import com.marcpetit.pokedex.network.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

sealed class PokemonListViewState {
    class ShowData(val list: List<PokemonListDisplay>) : PokemonListViewState()
    object Loading : PokemonListViewState()
    object Error : PokemonListViewState()
}

sealed class PokemonListEvent {
    object InitEvent : PokemonListEvent()
    class LoadMoreEvent(val offset: Int) : PokemonListEvent()
}

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) :
    ViewModel() {

    private val mutableViewState = MutableLiveData<PokemonListViewState>()
    val viewState: LiveData<PokemonListViewState> get() = mutableViewState

    fun dispatchEvent(event: PokemonListEvent) {
        when (event) {
            PokemonListEvent.InitEvent -> getPokemonList()
            is PokemonListEvent.LoadMoreEvent -> getPokemonList(event.offset)
        }
    }

    private fun getPokemonList(offset: Int = 0) {
        viewModelScope.launch(Dispatchers.IO) {
            mutableViewState.postValue(PokemonListViewState.Loading)
            try {
                mutableViewState.postValue(
                    PokemonListViewState.ShowData(
                        pokemonRepository.getPokemonList(offset)
                    )
                )
            } catch (e: Exception) {
                mutableViewState.postValue(PokemonListViewState.Error)
                Log.e("PokemonListViewModel", e.message.toString())
            }
        }
    }
}
