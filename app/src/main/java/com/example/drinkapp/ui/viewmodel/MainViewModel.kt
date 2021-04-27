package com.example.drinkapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.drinkapp.domain.Repo
import com.example.drinkapp.vo.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo:Repo): ViewModel() {

    private val tragosData = MutableLiveData<String>()

    fun setTrago(tragoNombre:String){
        tragosData.value = tragoNombre
    }

    init {
        setTrago("margarita")
    }

    val fetchTragosList = tragosData.distinctUntilChanged().switchMap { tragoNombre ->

        liveData(Dispatchers.IO) {
            emit(Resource.Loading())

            try {
                emit(repo.getTragosList(tragoNombre))
            }catch (e: Exception){
                emit(Resource.Failure(e))
            }
        }
    }
}