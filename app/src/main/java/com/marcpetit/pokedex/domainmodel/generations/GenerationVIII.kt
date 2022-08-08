package com.marcpetit.pokedex.domainmodel.generations

import com.google.gson.annotations.SerializedName
import com.marcpetit.pokedex.domainmodel.sprites.Icons

data class GenerationVIII(

    @SerializedName("icons") var icons: Icons? = Icons()

)
