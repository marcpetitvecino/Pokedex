package com.marcpetit.pokedex.domainmodel.generations

import com.google.gson.annotations.SerializedName
import com.marcpetit.pokedex.domainmodel.sprites.DiamondPearl
import com.marcpetit.pokedex.domainmodel.sprites.HeartgoldSoulsilver
import com.marcpetit.pokedex.domainmodel.sprites.Platinum

data class GenerationIV(

    @SerializedName("diamond-pearl") var diamondPearl: DiamondPearl? = DiamondPearl(),
    @SerializedName("heartgold-soulsilver") var heartgoldSoulsilver: HeartgoldSoulsilver? = HeartgoldSoulsilver(),
    @SerializedName("platinum") var platinum: Platinum? = Platinum()

)
