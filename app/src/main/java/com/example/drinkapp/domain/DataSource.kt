package com.example.drinkapp.domain

import com.example.drinkapp.data.model.Drink
import com.example.drinkapp.data.model.DrinkEntity
import com.example.drinkapp.vo.Resource

interface DataSource {

    suspend fun getTragoByName(tragoName:String): Resource<List<Drink>>
    suspend fun insertTragoIntoRoom(tragoName: DrinkEntity)
    suspend fun getTragoFavoritos(): Resource<List<DrinkEntity>>
}