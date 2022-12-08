package com.bagaspardanailham.pokedexapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bagaspardanailham.pokedexapp.data.local.PokeDao
import com.bagaspardanailham.pokedexapp.data.local.PokeDatabase
import com.bagaspardanailham.pokedexapp.data.local.model.MyPokeCollectionEntity
import com.bagaspardanailham.pokedexapp.data.remote.ApiService
import com.bagaspardanailham.pokedexapp.data.remote.response.ListPokemonResponse
import javax.inject.Inject
import javax.inject.Singleton
import com.bagaspardanailham.pokedexapp.data.remote.Result
import com.bagaspardanailham.pokedexapp.data.remote.response.PokemonByNameResponse
import retrofit2.HttpException

@Singleton
class PokeRepository @Inject constructor(private val apiService: ApiService, private val pokeDatabase: PokeDatabase) {

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
                    500 -> emit(
                        Result.Error(true, throwable.code(), throwable.response()?.errorBody())
                    )
                    else -> emit(
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

    fun getPokeByName(name: String): LiveData<Result<PokemonByNameResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getPokemonByName(name)
            emit(Result.Success(response))
        } catch (throwable: Throwable) {
            if (throwable is HttpException) {
                when (throwable.code()) {
                    404 -> emit(
                        Result.Error(true, throwable.code(), throwable.response()?.errorBody())
                    )
                    500 -> emit(
                        Result.Error(true, throwable.code(), throwable.response()?.errorBody())
                    )
                    else -> emit(
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

    // Insert Pokemon To Collection
    suspend fun insertPokemon(poke: MyPokeCollectionEntity) {
        pokeDatabase.pokeDao().insertPokemon(poke)
    }

    // Get Pokemon From Collection
    fun getPokemonCollection(): LiveData<List<MyPokeCollectionEntity>> {
        return pokeDatabase.pokeDao().getAllPokeCollection()
    }

    // Delete Pokemon from collection
    suspend fun deletePokemon(data: MyPokeCollectionEntity) {
        pokeDatabase.pokeDao().deleteNote(data)
    }

}