package com.marcpetit.pokedex.domainmodel.sprites

import com.google.gson.annotations.SerializedName
import com.marcpetit.pokedex.domainmodel.combat.Version

data class GameIndices(

    @SerializedName("game_index") var gameIndex: Int? = null,
    @SerializedName("version") var version: Version? = Version()

)
