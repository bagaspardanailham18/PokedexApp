package com.bagaspardanailham.pokedexapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagaspardanailham.pokedexapp.data.PokeRepository
import com.bagaspardanailham.pokedexapp.data.local.model.MyPokeCollectionEntity
import com.bagaspardanailham.pokedexapp.data.remote.Result
import com.bagaspardanailham.pokedexapp.data.remote.response.PokemonByNameResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeDetailViewModel @Inject constructor(private val pokeRepository: PokeRepository): ViewModel() {

    fun getPokeByName(name: String): LiveData<Result<PokemonByNameResponse>> = pokeRepository.getPokeByName(name)

    fun insertPokeToCollection(poke: MyPokeCollectionEntity) {
        viewModelScope.launch {
            pokeRepository.insertPokemon(poke)
        }
    }

}