package com.marcpetit.pokedex.domainmodel.generations

import com.google.gson.annotations.SerializedName
import com.marcpetit.pokedex.domainmodel.sprites.Crystal
import com.marcpetit.pokedex.domainmodel.sprites.Gold
import com.marcpetit.pokedex.domainmodel.sprites.Silver

data class GenerationII(

    @SerializedName("crystal") var crystal: Crystal? = Crystal(),
    @SerializedName("gold") var gold: Gold? = Gold(),
    @SerializedName("silver") var silver: Silver? = Silver()

)
