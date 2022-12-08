package com.bagaspardanailham.pokedexapp.ui.mypokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bagaspardanailham.pokedexapp.data.PokeRepository
import com.bagaspardanailham.pokedexapp.data.local.model.MyPokeCollectionEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPokemonViewModel @Inject constructor(private val pokeRepository: PokeRepository) : ViewModel() {

    fun getAllPokemonCollection(): LiveData<List<MyPokeCollectionEntity>> =
        pokeRepository.getPokemonCollection()

}