package com.marcpetit.pokedex.domainmodel.combat

import com.google.gson.annotations.SerializedName
import com.marcpetit.pokedex.domainmodel.generations.GenerationI
import com.marcpetit.pokedex.domainmodel.generations.GenerationII
import com.marcpetit.pokedex.domainmodel.generations.GenerationIII
import com.marcpetit.pokedex.domainmodel.generations.GenerationIV
import com.marcpetit.pokedex.domainmodel.generations.GenerationV
import com.marcpetit.pokedex.domainmodel.generations.GenerationVI
import com.marcpetit.pokedex.domainmodel.generations.GenerationVII
import com.marcpetit.pokedex.domainmodel.generations.GenerationVIII

data class Versions(

    @SerializedName("generation-i") var generationI: GenerationI? = GenerationI(),
    @SerializedName("generation-ii") var generationII: GenerationII? = GenerationII(),
    @SerializedName("generation-iii") var generationIII: GenerationIII? = GenerationIII(),
    @SerializedName("generation-iv") var generationIV: GenerationIV? = GenerationIV(),
    @SerializedName("generation-v") var generationV: GenerationV? = GenerationV(),
    @SerializedName("generation-vi") var generationVI: GenerationVI? = GenerationVI(),
    @SerializedName("generation-vii") var generationVII: GenerationVII? = GenerationVII(),
    @SerializedName("generation-viii") var generationVIII: GenerationVIII? = GenerationVIII()

)
