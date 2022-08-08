package com.marcpetit.pokedex.domainmodel.generations

import com.google.gson.annotations.SerializedName
import com.marcpetit.pokedex.domainmodel.sprites.BlackWhite

data class GenerationV(

    @SerializedName("black-white") var blackWhite: BlackWhite? = BlackWhite()

)
