package com.marcpetit.pokedex.domainmodel.generations

import com.google.gson.annotations.SerializedName
import com.marcpetit.pokedex.domainmodel.sprites.RedBlue
import com.marcpetit.pokedex.domainmodel.sprites.Yellow

data class GenerationI(

    @SerializedName("red-blue") var redBlue: RedBlue? = RedBlue(),
    @SerializedName("yellow") var yellow: Yellow? = Yellow()

)
