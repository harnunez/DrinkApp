package com.example.drinkapp.domain

import com.example.drinkapp.data.model.Drink
import com.example.drinkapp.vo.Resource

interface Repo {

    fun getTragosList(): Resource<List<Drink>>
}