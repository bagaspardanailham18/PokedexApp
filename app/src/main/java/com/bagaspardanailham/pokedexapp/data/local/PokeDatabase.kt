package com.bagaspardanailham.pokedexapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bagaspardanailham.pokedexapp.data.local.model.MyPokeCollectionEntity

@Database(entities = [MyPokeCollectionEntity::class], version = 1)
abstract class PokeDatabase: RoomDatabase() {

    abstract fun pokeDao(): PokeDao

    companion object {
        @Volatile
        private var INSTANCE: PokeDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): PokeDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    PokeDatabase::class.java, "poke_database"
                )
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }

}