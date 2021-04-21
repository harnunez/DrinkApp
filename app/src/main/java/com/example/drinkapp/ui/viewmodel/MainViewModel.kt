package com.example.drinkapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.drinkapp.domain.Repo
import com.example.drinkapp.vo.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo:Repo): ViewModel() {

    val fetchTragosList = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit(repo.getTragosList())
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }

    }
}