package com.marcpetit.pokedex.domainmodel.combat

import com.google.gson.annotations.SerializedName

data class Type(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)
