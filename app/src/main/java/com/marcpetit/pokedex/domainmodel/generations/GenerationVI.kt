package com.marcpetit.pokedex.domainmodel.generations

import com.google.gson.annotations.SerializedName
import com.marcpetit.pokedex.domainmodel.sprites.OmegarubyAlphasapphire
import com.marcpetit.pokedex.domainmodel.sprites.XY

data class GenerationVI(

    @SerializedName("omegaruby-alphasapphire") var omegarubyAlphasapphire: OmegarubyAlphasapphire? = OmegarubyAlphasapphire(),
    @SerializedName("x-y") var xY: XY? = XY()

)
