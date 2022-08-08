package com.marcpetit.pokedex.network

import com.marcpetit.pokedex.domainmodel.Pokemon
import com.marcpetit.pokedex.domainmodel.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {
    @GET("pokemon?limit=20}")
    suspend fun getPokemonList(@Query("offset") offset: Int = 0): Response<PokemonList>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name: String): Response<Pokemon>
}
