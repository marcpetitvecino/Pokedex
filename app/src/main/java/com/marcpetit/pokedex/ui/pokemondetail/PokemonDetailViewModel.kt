package com.marcpetit.pokedex.ui.pokemondetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcpetit.pokedex.R
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

    fun getTypeDrawable(type: String): Int {
        return when (type) {
            "bug" -> R.drawable.ic_bug_type
            "dark" -> R.drawable.ic_dark_type
            "dragon" -> R.drawable.ic_dragon_type
            "electric" -> R.drawable.ic_electric_type
            "fairy" -> R.drawable.ic_fairy_type
            "fighting" -> R.drawable.ic_fighting_type
            "fire" -> R.drawable.ic_fire_type
            "flying" -> R.drawable.ic_flying_type
            "ghost" -> R.drawable.ic_ghost_type
            "grass" -> R.drawable.ic_grass_type
            "ground" -> R.drawable.ic_ground_type
            "ice" -> R.drawable.ic_ice_type
            "normal" -> R.drawable.ic_normal_type
            "poison" -> R.drawable.ic_poison_type
            "psychic" -> R.drawable.ic_psychic_type
            "rock" -> R.drawable.ic_rock_type
            "steel" -> R.drawable.ic_steel_type
            "water" -> R.drawable.ic_water_type
            else -> R.drawable.ic_normal_type
        }
    }
}
