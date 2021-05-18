package com.example.drinkapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.drinkapp.data.model.DrinkEntity
import com.example.drinkapp.domain.TragosDao

@Database(
    entities = arrayOf(DrinkEntity::class),
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tragoDao(): TragosDao

    companion object{

        private var INSTANCE : AppDatabase?=null

        fun getDataBase (context:Context): AppDatabase{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"tabla_tragos").build()
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}