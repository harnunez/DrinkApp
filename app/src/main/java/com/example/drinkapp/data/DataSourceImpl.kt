package com.example.drinkapp.data

import com.example.drinkapp.AppDatabase
import com.example.drinkapp.data.model.Drink
import com.example.drinkapp.data.model.DrinkEntity
import com.example.drinkapp.domain.DataSource
import com.example.drinkapp.vo.Resource
import com.example.drinkapp.vo.RetrofitClient

class DataSourceImpl(private val appDataBase: AppDatabase) : DataSource {

    override suspend fun getTragoByName(tragoName:String):Resource<List<Drink>>{
        return Resource.Succes(RetrofitClient.webservice.getTragoByName(tragoName).drinkList)
    }

    override suspend fun insertTragoIntoRoom(tragoName: DrinkEntity){
        appDataBase.tragoDao().insertFavorite(tragoName)
    }

    override suspend fun getTragoFavoritos(): Resource<List<DrinkEntity>> {
        return Resource.Succes(appDataBase.tragoDao().getAllFavotiteDrinks())
    }

}