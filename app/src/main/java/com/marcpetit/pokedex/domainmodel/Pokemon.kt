package com.marcpetit.pokedex.domainmodel

import com.google.gson.annotations.SerializedName
import com.marcpetit.pokedex.domainmodel.combat.Abilities
import com.marcpetit.pokedex.domainmodel.combat.Item
import com.marcpetit.pokedex.domainmodel.combat.Moves
import com.marcpetit.pokedex.domainmodel.combat.Species
import com.marcpetit.pokedex.domainmodel.combat.Stats
import com.marcpetit.pokedex.domainmodel.combat.Types
import com.marcpetit.pokedex.domainmodel.generations.Generation
import com.marcpetit.pokedex.domainmodel.sprites.Forms
import com.marcpetit.pokedex.domainmodel.sprites.GameIndices
import com.marcpetit.pokedex.domainmodel.sprites.Sprites

data class Pokemon(

    @SerializedName("abilities") var abilities: ArrayList<Abilities> = arrayListOf(),
    @SerializedName("base_experience") var baseExperience: Int? = null,
    @SerializedName("forms") var forms: ArrayList<Forms> = arrayListOf(),
    @SerializedName("game_indices") var gameIndices: ArrayList<GameIndices> = arrayListOf(),
    @SerializedName("height") var height: Int? = null,
    @SerializedName("held_items") var heldItems: ArrayList<Item> = arrayListOf(),
    @SerializedName("id") var id: Int? = null,
    @SerializedName("is_default") var isDefault: Boolean? = null,
    @SerializedName("location_area_encounters") var locationAreaEncounters: String? = null,
    @SerializedName("moves") var moves: ArrayList<Moves> = arrayListOf(),
    @SerializedName("name") var name: String? = null,
    @SerializedName("order") var order: Int? = null,
    @SerializedName("past_types") var pastTypes: ArrayList<Generation> = arrayListOf(),
    @SerializedName("species") var species: Species? = Species(),
    @SerializedName("sprites") var sprites: Sprites? = Sprites(),
    @SerializedName("stats") var stats: ArrayList<Stats> = arrayListOf(),
    @SerializedName("types") var types: ArrayList<Types> = arrayListOf(),
    @SerializedName("weight") var weight: Int? = null

)

data class PokemonListDisplay(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("sprites") var sprites: Sprites? = Sprites(),
)
