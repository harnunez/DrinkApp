package com.example.drinkapp.domain

import com.example.drinkapp.data.DataSource
import com.example.drinkapp.data.model.Drink
import com.example.drinkapp.data.model.DrinkEntity
import com.example.drinkapp.vo.Resource

class RepoImpl(private val dataSource: DataSource): Repo{

    override suspend fun getTragosList(tragoName:String): Resource<List<Drink>> {
        return dataSource.getTragoByName(tragoName)
    }

    override suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>> {
        return dataSource.getTragoFavoritos()
    }

    override suspend fun insertTrago(trago: DrinkEntity) {
        dataSource.insertTragoIntoRoom(trago)
    }
}