package com.marcpetit.pokedex.domainmodel.combat

import com.google.gson.annotations.SerializedName

data class MoveLearnMethod(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)
