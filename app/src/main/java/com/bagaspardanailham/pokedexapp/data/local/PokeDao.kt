package com.bagaspardanailham.pokedexapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bagaspardanailham.pokedexapp.data.local.model.MyPokeCollectionEntity

@Dao
interface PokeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokemon(poke: MyPokeCollectionEntity)

    @Query("SELECT * FROM my_poke_collection")
    fun getAllPokeCollection(): LiveData<List<MyPokeCollectionEntity>>

    @Delete
    suspend fun deleteNote(poke: MyPokeCollectionEntity)

}