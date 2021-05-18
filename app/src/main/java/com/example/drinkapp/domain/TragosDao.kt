package com.example.drinkapp.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinkapp.data.model.DrinkEntity

@Dao
interface TragosDao {

    @Query("SELECT * FROM DrinkEntity")
    suspend fun getAllFavotiteDrinks():List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(trago:DrinkEntity)
}