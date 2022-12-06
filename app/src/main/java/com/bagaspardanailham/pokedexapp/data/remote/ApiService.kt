package com.bagaspardanailham.pokedexapp.data.remote

import com.bagaspardanailham.pokedexapp.data.remote.response.ListPokemonResponse
import com.bagaspardanailham.pokedexapp.data.remote.response.PokemonByIdResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon")
    suspend fun getAllPokemon(): ListPokemonResponse

    @GET("pokemon/{id}/")
    fun getPokemonByName(
        @Path("id") id: Int
    ): PokemonByIdResponse



}