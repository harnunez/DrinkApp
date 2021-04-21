package com.example.drinkapp.data

import com.example.drinkapp.data.model.Drink
import com.example.drinkapp.vo.Resource

class DataSource {

     val generateTragosList = Resource.Succes(listOf(
        Drink("https://i.pinimg.com/originals/dd/53/ca/dd53ca5f12947e9b573b4c16bff4a00a.png","Margarita","Con azucar y vodka"),
        Drink("https://images.clarin.com/2019/04/11/aun-hay-distintas-versiones-sobre___CmdFSZZQK_720x0__1.jpg","Fernet","Con coca y hielo"),
        Drink("https://www.pritty.com.ar/img/Imagenes/asicomenzo/9.jpg","Toro","Toro con pritty"),
        Drink("http://assets.iprofesional.com/assets/jpg/2013/01/371671.jpg","Gancia","Gancia con sprite")
    ))


}