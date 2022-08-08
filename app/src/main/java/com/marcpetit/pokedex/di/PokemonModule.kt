package com.marcpetit.pokedex.di

import com.marcpetit.pokedex.network.PokemonAPI
import com.marcpetit.pokedex.network.PokemonRepository
import com.marcpetit.pokedex.network.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PokemonModule {
    @Singleton
    @Provides
    fun providePokemonApi(retrofit: Retrofit): PokemonAPI = retrofit.create(PokemonAPI::class.java)

    @Singleton
    @Provides
    fun providePokemonRepository(pokemonAPI: PokemonAPI): PokemonRepository = PokemonRepositoryImpl(pokemonAPI)
}
