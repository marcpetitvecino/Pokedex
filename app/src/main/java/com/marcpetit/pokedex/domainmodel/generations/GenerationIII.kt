package com.marcpetit.pokedex.domainmodel.generations

import com.google.gson.annotations.SerializedName
import com.marcpetit.pokedex.domainmodel.sprites.Emerald
import com.marcpetit.pokedex.domainmodel.sprites.FireredLeafgreen
import com.marcpetit.pokedex.domainmodel.sprites.RubySapphire

data class GenerationIII(

    @SerializedName("emerald") var emerald: Emerald? = Emerald(),
    @SerializedName("firered-leafgreen") var fireredLeafgreen: FireredLeafgreen? = FireredLeafgreen(),
    @SerializedName("ruby-sapphire") var rubySapphire: RubySapphire? = RubySapphire()

)
