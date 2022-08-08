package com.marcpetit.pokedex.domainmodel.combat

import com.google.gson.annotations.SerializedName

data class Types(

    @SerializedName("slot") var slot: Int? = null,
    @SerializedName("type") var type: Type? = Type()

)
