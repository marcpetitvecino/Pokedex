package com.marcpetit.pokedex.network

import com.marcpetit.pokedex.domainmodel.Pokemon
import com.marcpetit.pokedex.domainmodel.PokemonList
import com.marcpetit.pokedex.domainmodel.PokemonListDisplay
import java.lang.Exception
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonAPI: PokemonAPI
) : PokemonRepository {

    private var pokemonList: MutableList<PokemonListDisplay> = mutableListOf()

    override suspend fun getPokemonList(offset: Int): List<PokemonListDisplay> {
        val listResponse = pokemonAPI.getPokemonList(offset)
        if (listResponse.isSuccessful) {
            listResponse.body()?.let {
                getFullPokemonList(it)
            }
        } else {
            throw Exception()
        }
        return pokemonList
    }

    override suspend fun getPokemon(name: String): Pokemon {
        val response = pokemonAPI.getPokemonDetail(name)
        if (response.isSuccessful) {
            return response.body() ?: Pokemon()
        } else {
            throw Exception()
        }
    }

    private suspend fun getFullPokemonList(list: PokemonList) {
        for (pokemon in list.results) {
            val pokemonResponse = pokemonAPI.getPokemonDetail(pokemon.name)
            if (pokemonResponse.isSuccessful) {
                pokemonResponse.body()?.let {
                    with(it) {
                        pokemonList.add(
                            PokemonListDisplay(
                                id,
                                name,
                                sprites
                            )
                        )
                    }
                }
            } else {
                throw Exception()
            }
        }
    }
}
