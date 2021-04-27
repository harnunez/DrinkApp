package com.example.drinkapp.data

import com.example.drinkapp.data.model.Drink
import com.example.drinkapp.vo.Resource
import com.example.drinkapp.vo.RetrofitClient

class DataSource {

    suspend fun getTragoByName(tragoName:String):Resource<List<Drink>>{
        return Resource.Succes(RetrofitClient.webservice.getTragoByName(tragoName).drinkList)
    }

}