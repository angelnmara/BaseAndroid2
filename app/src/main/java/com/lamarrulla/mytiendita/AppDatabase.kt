package com.lamarrulla.mytiendita

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lamarrulla.mytiendita.data.dao.DatosClienteDao
import com.lamarrulla.mytiendita.data.model.DatosCliente

@Database(entities = arrayOf(DatosCliente::class), version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun datosClienteDao(): DatosClienteDao
    companion object{
        private var INSTANCE: AppDatabase?=null
        fun getDatabase(context: Context):AppDatabase{
            INSTANCE= INSTANCE?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "dbExample")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
            return INSTANCE!!
        }
    }
}