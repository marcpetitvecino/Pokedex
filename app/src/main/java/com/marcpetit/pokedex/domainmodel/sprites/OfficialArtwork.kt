package com.marcpetit.pokedex.domainmodel.sprites

import com.google.gson.annotations.SerializedName

data class OfficialArtwork(

    @SerializedName("front_default") var frontDefault: String? = null

)
