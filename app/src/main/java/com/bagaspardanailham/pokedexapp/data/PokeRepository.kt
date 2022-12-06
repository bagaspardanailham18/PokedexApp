package com.bagaspardanailham.pokedexapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bagaspardanailham.pokedexapp.data.remote.ApiService
import com.bagaspardanailham.pokedexapp.data.remote.response.ListPokemonResponse
import javax.inject.Inject
import javax.inject.Singleton
import com.bagaspardanailham.pokedexapp.data.remote.Result
import com.bagaspardanailham.pokedexapp.data.remote.response.PokemonByIdResponse
import retrofit2.HttpException

@Singleton
class PokeRepository @Inject constructor(private val apiService: ApiService) {

    fun getAllPoke(): LiveData<Result<ListPokemonResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllPokemon()
            emit(Result.Success(response))
        } catch (throwable: Throwable) {
            if (throwable is HttpException) {
                when (throwable.code()) {
                    404 -> emit(
                        Result.Error(true, throwable.code(), throwable.response()?.errorBody())
                    )
                }
            } else {
                emit(
                    Result.Error(false, null, null)
                )
            }
        }
    }

//    fun getPokeByName(id: Int): LiveData<Result<PokemonByIdResponse>> = liveData {
//        emit(Result.Loading)
//        try {
//            val response = apiService.getPokemonByName(id)
//            emit(Result.Success(response))
//        } catch (e: Exception) {
//            e.printStackTrace()
//            emit(Result.Error(e.message.toString()))
//        }
//    }

}