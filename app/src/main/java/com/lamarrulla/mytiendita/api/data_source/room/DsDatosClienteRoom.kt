package com.lamarrulla.mytiendita.api.data_source.room

import com.lamarrulla.mytiendita.AppDatabase
import com.lamarrulla.mytiendita.data.Res
import com.lamarrulla.mytiendita.data.model.DatosCliente

open class DsDatosClienteRoom(private val appDatabase: AppDatabase) {
    suspend fun getDatosClienteRoom():Res<List<DatosCliente>> {
        return Res.Success(appDatabase.datosClienteDao().getAll())
    }
    suspend fun getDatosClienteByIdRoom(idDatosCliente: Int):Res<DatosCliente>{
        return Res.Success(appDatabase.datosClienteDao().findById(idDatosCliente))
    }
    suspend fun insertDatosClienteRoom(datosCliente: DatosCliente){
        appDatabase.datosClienteDao().insert(datosCliente);
    }
    suspend fun insertDatosClienteListRoom(datosClientes:List<DatosCliente>): Res<List<Long>>{
        return Res.Success(appDatabase.datosClienteDao().insertList(datosClientes))
    }
    suspend fun deleteDatosClienteRom(datosCliente: DatosCliente){
        appDatabase.datosClienteDao().delete(datosCliente)
    }
}