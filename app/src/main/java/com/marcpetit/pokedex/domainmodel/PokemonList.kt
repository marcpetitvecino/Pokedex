package com.marcpetit.pokedex.domainmodel

import com.google.gson.annotations.SerializedName

data class PokemonList(
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: List<Results>
)
