package com.bagaspardanailham.pokedexapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bagaspardanailham.pokedexapp.data.PokeRepository
import com.bagaspardanailham.pokedexapp.data.remote.Result
import com.bagaspardanailham.pokedexapp.data.remote.response.ListPokemonResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val pokeRepository: PokeRepository) : ViewModel() {

    fun getAllPoke(): LiveData<Result<ListPokemonResponse>> = pokeRepository.getAllPoke()

}