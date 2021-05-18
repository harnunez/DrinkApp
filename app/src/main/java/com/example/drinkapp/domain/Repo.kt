package com.example.drinkapp.domain

import com.example.drinkapp.data.model.Drink
import com.example.drinkapp.data.model.DrinkEntity
import com.example.drinkapp.vo.Resource

interface Repo {

    suspend fun getTragosList(tragoName:String): Resource<List<Drink>>
    suspend fun getTragosFavoritos():Resource<List<DrinkEntity>>
    suspend fun insertTrago(trago:DrinkEntity)


}