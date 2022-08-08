package com.marcpetit.pokedex.network

import com.marcpetit.pokedex.domainmodel.Pokemon
import com.marcpetit.pokedex.domainmodel.PokemonListDisplay

interface PokemonRepository {
    suspend fun getPokemonList(offset: Int = 0): List<PokemonListDisplay>
    suspend fun getPokemon(name: String): Pokemon
}
